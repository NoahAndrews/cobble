import 'package:cobble/domain/logging.dart';
import 'package:cobble/infrastructure/datasources/web_services.dart';
import 'package:cobble/localization/localization.dart';
import 'package:cobble/ui/common/components/cobble_card.dart';
import 'package:cobble/ui/common/components/cobble_tile.dart';
import 'package:cobble/ui/common/icons/fonts/rebble_icons.dart';
import 'package:cobble/ui/home/tabs/widget_library.dart';
import 'package:cobble/ui/router/cobble_scaffold.dart';
import 'package:cobble/ui/router/cobble_screen.dart';
import 'package:cobble/ui/screens/about.dart';
import 'package:cobble/ui/screens/calendar.dart';
import 'package:cobble/ui/screens/health.dart';
import 'package:cobble/ui/screens/notifications.dart';
import 'package:cobble/ui/screens/placeholder_screen.dart';
import 'package:cobble/ui/theme/with_cobble_theme.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg_provider/flutter_svg_provider.dart';
import 'package:hooks_riverpod/hooks_riverpod.dart';

class Settings extends ConsumerWidget implements CobbleScreen {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final userData = ref.watch(wsUserProvider);
    return CobbleScaffold.tab(
      title: tr.settings.title,
      child: ListView(
        children: [
          userData.when(
              data: (data) {
                return CobbleCard.inList(
                  leading: Svg('images/app_icon.svg'),
                  title: tr.settings.account,
                  subtitle: data != null ? '${data.name}\n${data.email}' : 'Not logged in', // TODO(Noah): Move to en.json
                  child: Column(
                    children: [
                      CobbleTile.info(
                        leading: RebbleIcons.dictation_microphone,
                        title: tr.settings.subscription.title,
                        subtitle: tr.settings.subscription.subtitleNotSubscribed,
                      ),
                      CobbleTile.info(
                        leading: RebbleIcons.timeline_pin,
                        title: tr.settings.timeline.title,
                        subtitle: tr.settings.timeline.subtitle,
                      ),
                    ],
                  ),
                  actions: [
                    CobbleCardAction(
                      label: tr.settings.signOut,
                      onPressed: () {},
                    ),
                    CobbleCardAction(
                      label: tr.settings.manageAccount,
                      onPressed: () {},
                    ),
                  ],
                );
              },
              error: (error, stackTrace) {
                Log.e('Failed to load user info: error=$error stackTrace=$stackTrace');
                return Text('Failed to load user information');
              },
              loading: () {
                return CircularProgressIndicator();
              }
          ),
          CobbleTile.navigation(
            leading: RebbleIcons.notification,
            title: tr.settings.notificationsAndMuting,
            navigateTo: Notifications(),
          ),
          CobbleTile.navigation(
            leading: RebbleIcons.health_heart,
            title: tr.settings.health,
            navigateTo: Health(),
          ),
          CobbleTile.navigation(
            leading: RebbleIcons.calendar,
            title: tr.settings.calendar,
            navigateTo: Calendar(),
          ),
          CobbleTile.navigation(
            leading: RebbleIcons.sms_messages,
            title: tr.settings.messagesAndCannedReplies,
            navigateTo: PlaceholderScreen(),
          ),
          CobbleTile.navigation(
            leading: RebbleIcons.system_language,
            title: tr.settings.languageAndVoice,
            navigateTo: PlaceholderScreen(),
          ),
          CobbleTile.navigation(
            leading: RebbleIcons.analytics,
            title: tr.settings.analytics,
            navigateTo: PlaceholderScreen(),
          ),
          CobbleTile.navigation(
            leading: RebbleIcons.about_app,
            title: tr.settings.aboutAndSupport,
            navigateTo: About(),
          ),
          CobbleTile.navigation(
            leading: RebbleIcons.developer_settings,
            title: tr.settings.developerOptions,
            navigateTo: PlaceholderScreen(),
          ),
          if (kDebugMode)
            CobbleTile.navigation(
              leading: RebbleIcons.developer_connection_console,
              title: tr.settings.widgetLibrary,
              navigateTo: WidgetLibrary(),
            ),
        ],
      ),
    );
  }
}
