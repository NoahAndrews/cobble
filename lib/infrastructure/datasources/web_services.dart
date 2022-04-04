import 'dart:async';
import 'dart:convert';

import 'package:cobble/infrastructure/datasources/preferences.dart';
import 'package:hooks_riverpod/hooks_riverpod.dart';
import 'package:http/http.dart' as http;

import '../../domain/logging.dart';

final wsTokenProvider = Provider((ref) {
  final bootUrl = ref.watch(bootUrlProvider).value;
  if (bootUrl == null) {
    return null;
  } else {
    return Uri.parse(bootUrl).queryParameters[_tokenQueryParameter];
  }
});

// Works: https://boot.rebble.io/api/stage2/android/v3/1?access_token=W35chqeGs3F3htobjILuGTsoFOG5zQ&amp;t=1648308070
// Fails: https://boot.rebble.io/api/stage2//android/v3/1405/?access_token=yNUnVCQ31NNfGlCJFzN1O38YiCxGa1&t=1648326594

// TODO(NoahAndrews): Once riverpod 2.0 is out, provide a cacheTime parameter to
//                    autoDispose() with a value of 1 hour
final bootDataProvider = FutureProvider.autoDispose<Map<String, dynamic>?>((ref) async {
  final unparsedBootUrl = ref.watch(bootUrlProvider).value;
  Log.d('bootDataProvider: bootUrl=$unparsedBootUrl'); // TODO(Noah): Check if this is initially null
  if (unparsedBootUrl == null) {
    return null;
  } else {
    Log.d('Retrieving boot data');
    Uri bootUrl = Uri.parse(unparsedBootUrl);
    final adjustedPathSegments = List<String>.from(bootUrl.pathSegments);
    adjustedPathSegments.remove(''); // Necessary in case the boot URL ends in a slash
    // TODO(NoahAndrews): Use https://pub.dev/packages/package_info_plus to get the build number
    adjustedPathSegments.addAll(['android', 'v3', '1405']); //TODO: iOS specific path when using iOS?
      Uri adjustedUrl = bootUrl.replace(pathSegments: adjustedPathSegments);
    final json = await http.read(adjustedUrl);
    Log.d('boot info: $json'); // TODO(Noah): Remove
    final result = jsonDecode(json);
    // We successfully retrieved and parsed the result, so we should cache it instead of
    // auto-disposing it.
    ref.maintainState = true;
    return result;
  }
});

// TODO(NoahAndrews): Once riverpod 2.0 is out, provide a cacheTime parameter to
//                    autoDispose() with a value of 1 hour
// TODO(NoahAndrews): As long as `token` is non-null, hang on to the previous WSUser instance
final wsUserProvider = FutureProvider.autoDispose((ref) async {
  final bootData = await ref.watch(bootDataProvider.future);
  final token = ref.watch(wsTokenProvider);
  if (bootData == null) {
    return null;
  } else {
    Uri userUri = Uri.parse(bootData['config']['links']['authentication/me'] +
        "?access_token=$token");
    final userJson = await http.read(userUri);
    Map<String, dynamic> user = jsonDecode(userJson);

    final String? email = user['email'];
    final String? id = user['id'];
    final String? name = user['name'];

    if (email != null && id != null && name != null) {
      return WSUser(user['email'], user['id'], user['name']);
    } else {
      return null;
    }
  }
});

// TODO(NoahAndrews): See conversation around here to get a more general-purpose token
//                    https://discord.com/channels/221364737269694464/256368641732247552/960399010236084244

class WSUser {
  final String email;
  final String id;
  final String name;
  WSUser(this.email, this.id, this.name);
}

const _tokenQueryParameter = 'access_token';
