import 'package:cobble/infrastructure/datasources/preferences.dart';
import 'package:cobble/infrastructure/datasources/web_services.dart';
import 'package:cobble/localization/localization.dart';
import 'package:cobble/ui/home/home_page.dart';
import 'package:cobble/ui/router/cobble_navigator.dart';
import 'package:cobble/ui/router/cobble_scaffold.dart';
import 'package:cobble/ui/router/cobble_screen.dart';
import 'package:flutter/material.dart';
import 'package:hooks_riverpod/hooks_riverpod.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../../domain/logging.dart';

class RebbleSetupSuccess extends HookConsumerWidget implements CobbleScreen {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final preferences = ref.watch(preferencesProvider);
    // TODO(NoahAndrews): At the moment, the user data will always be null
    //                    because we're changing the boot URL shared pref from
    //                    Kotlin, which does not trigger the riverpod
    //                    SharedPrefs stream
    final userData = ref.watch(wsUserProvider);
    return CobbleScaffold.page(
      title: tr.setup.success.title,
      child: Column(
        children: <Widget>[
          Text(
            tr.setup.success.subtitle,
            style: Theme.of(context).textTheme.headline3,
          ),
          userData.when(
              data: (data) {
                if (data == null) {
                  return Text(" ");
                } else {
                  return Text(tr.setup.success.welcome(name: data.name));
                }
              },
              error: (error, stackTrace) {
                return Text(" ");

              },
              loading: () {
                return CircularProgressIndicator();
              }
          )
        ],
      ),
      floatingActionButton: FloatingActionButton.extended(
          onPressed: () {
            SharedPreferences.getInstance().then((prefs) async {
              await preferences.data?.value.setHasBeenConnected();
              await preferences.data?.value.setWasSetupSuccessful(true);
            }).then((_) {
              context.pushAndRemoveAllBelow(HomePage());
            });
          },
          label: Text(tr.setup.success.fab)),
    );
  }
}
