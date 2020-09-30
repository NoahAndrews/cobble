// Autogenerated from Pigeon (v0.1.6), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package io.rebble.fossil.pigeons;

import java.util.ArrayList;
import java.util.HashMap;

import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;

/**
 * Generated class from Pigeon.
 */
@SuppressWarnings("unused")
public class Pigeons {

    /**
     * Generated class from Pigeon that represents data sent in messages.
     */
    public static class ListWrapper {
        private ArrayList value;

        public ArrayList getValue() {
            return value;
        }

        public void setValue(ArrayList setterArg) {
            this.value = setterArg;
        }

        HashMap toMap() {
            HashMap<String, Object> toMapResult = new HashMap<>();
            toMapResult.put("value", value);
            return toMapResult;
        }

        static ListWrapper fromMap(HashMap map) {
            ListWrapper fromMapResult = new ListWrapper();
            Object value = map.get("value");
            fromMapResult.value = (ArrayList) value;
            return fromMapResult;
        }
    }

    /**
     * Generated class from Pigeon that represents data sent in messages.
     */
    public static class BooleanWrapper {
        private Boolean value;

        public Boolean getValue() {
            return value;
        }

        public void setValue(Boolean setterArg) {
            this.value = setterArg;
        }

        HashMap toMap() {
            HashMap<String, Object> toMapResult = new HashMap<>();
            toMapResult.put("value", value);
            return toMapResult;
        }

        static BooleanWrapper fromMap(HashMap map) {
            BooleanWrapper fromMapResult = new BooleanWrapper();
            Object value = map.get("value");
            fromMapResult.value = (Boolean) value;
            return fromMapResult;
        }
    }

    /**
     * Generated class from Pigeon that represents data sent in messages.
     */
    public static class NumberWrapper {
        private Long value;

        public Long getValue() {
            return value;
        }

        public void setValue(Long setterArg) {
            this.value = setterArg;
        }

        HashMap toMap() {
            HashMap<String, Object> toMapResult = new HashMap<>();
            toMapResult.put("value", value);
            return toMapResult;
        }

        static NumberWrapper fromMap(HashMap map) {
            NumberWrapper fromMapResult = new NumberWrapper();
            Object value = map.get("value");
            fromMapResult.value = (value == null) ? null : ((value instanceof Integer) ? (Integer) value : (Long) value);
            return fromMapResult;
        }
    }

    /**
     * Generated interface from Pigeon that represents a handler of messages from Flutter.
     */
    public interface NotificationsControl {
        void sendTestNotification();

        /**
         * Sets up an instance of `NotificationsControl` to handle messages through the `binaryMessenger`
         */
        static void setup(BinaryMessenger binaryMessenger, NotificationsControl api) {
            {
                BasicMessageChannel<Object> channel =
                        new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NotificationsControl.sendTestNotification", new StandardMessageCodec());
                if (api != null) {
                    channel.setMessageHandler((message, reply) -> {
                        HashMap<String, HashMap> wrapped = new HashMap<>();
                        try {
                            api.sendTestNotification();
                            wrapped.put("result", null);
                        } catch (Exception exception) {
                            wrapped.put("error", wrapError(exception));
                        }
                        reply.reply(wrapped);
                    });
                } else {
                    channel.setMessageHandler(null);
                }
            }
        }
    }

    /**
     * Generated interface from Pigeon that represents a handler of messages from Flutter.
     */
    public interface ScanControl {
        void startBleScan();

        void startClassicScan();

        /**
         * Sets up an instance of `ScanControl` to handle messages through the `binaryMessenger`
         */
        static void setup(BinaryMessenger binaryMessenger, ScanControl api) {
            {
                BasicMessageChannel<Object> channel =
                        new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.ScanControl.startBleScan", new StandardMessageCodec());
                if (api != null) {
                    channel.setMessageHandler((message, reply) -> {
                        HashMap<String, HashMap> wrapped = new HashMap<>();
                        try {
                            api.startBleScan();
                            wrapped.put("result", null);
                        } catch (Exception exception) {
                            wrapped.put("error", wrapError(exception));
                        }
                        reply.reply(wrapped);
                    });
                } else {
                    channel.setMessageHandler(null);
                }
            }
            {
                BasicMessageChannel<Object> channel =
                        new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.ScanControl.startClassicScan", new StandardMessageCodec());
                if (api != null) {
                    channel.setMessageHandler((message, reply) -> {
                        HashMap<String, HashMap> wrapped = new HashMap<>();
                        try {
                            api.startClassicScan();
                            wrapped.put("result", null);
                        } catch (Exception exception) {
                            wrapped.put("error", wrapError(exception));
                        }
                        reply.reply(wrapped);
                    });
                } else {
                    channel.setMessageHandler(null);
                }
            }
        }
    }

    /**
     * Generated class from Pigeon that represents Flutter messages that can be called from Java.
     */
    public static class ScanCallbacks {
        private final BinaryMessenger binaryMessenger;

        public ScanCallbacks(BinaryMessenger argBinaryMessenger) {
            this.binaryMessenger = argBinaryMessenger;
        }

        public interface Reply<T> {
            void reply(T reply);
        }

        public void onScanUpdate(ListWrapper argInput, Reply<Void> callback) {
            BasicMessageChannel<Object> channel =
                    new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.ScanCallbacks.onScanUpdate", new StandardMessageCodec());
            HashMap inputMap = argInput.toMap();
            channel.send(inputMap, channelReply -> {
                callback.reply(null);
            });
        }

        public void onScanStarted(Reply<Void> callback) {
            BasicMessageChannel<Object> channel =
                    new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.ScanCallbacks.onScanStarted", new StandardMessageCodec());
            channel.send(null, channelReply -> {
                callback.reply(null);
            });
        }

        public void onScanStopped(Reply<Void> callback) {
            BasicMessageChannel<Object> channel =
                    new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.ScanCallbacks.onScanStopped", new StandardMessageCodec());
            channel.send(null, channelReply -> {
                callback.reply(null);
            });
        }
    }

    /**
     * Generated interface from Pigeon that represents a handler of messages from Flutter.
     */
    public interface AppLifecycleControl {
        BooleanWrapper waitForBoot();

        /**
         * Sets up an instance of `AppLifecycleControl` to handle messages through the `binaryMessenger`
         */
        static void setup(BinaryMessenger binaryMessenger, AppLifecycleControl api) {
            {
                BasicMessageChannel<Object> channel =
                        new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.AppLifecycleControl.waitForBoot", new StandardMessageCodec());
                if (api != null) {
                    channel.setMessageHandler((message, reply) -> {
                        HashMap<String, HashMap> wrapped = new HashMap<>();
                        try {
                            BooleanWrapper output = api.waitForBoot();
                            wrapped.put("result", output.toMap());
                        } catch (Exception exception) {
                            wrapped.put("error", wrapError(exception));
                        }
                        reply.reply(wrapped);
                    });
                } else {
                    channel.setMessageHandler(null);
                }
            }
        }
    }

    /**
     * Generated interface from Pigeon that represents a handler of messages from Flutter.
     */
    public interface ConnectionControl {
        BooleanWrapper isConnected();

        void connectToWatch(NumberWrapper arg);

        void sendRawPacket(ListWrapper arg);

        /**
         * Sets up an instance of `ConnectionControl` to handle messages through the `binaryMessenger`
         */
        static void setup(BinaryMessenger binaryMessenger, ConnectionControl api) {
            {
                BasicMessageChannel<Object> channel =
                        new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.ConnectionControl.isConnected", new StandardMessageCodec());
                if (api != null) {
                    channel.setMessageHandler((message, reply) -> {
                        HashMap<String, HashMap> wrapped = new HashMap<>();
                        try {
                            BooleanWrapper output = api.isConnected();
                            wrapped.put("result", output.toMap());
                        } catch (Exception exception) {
                            wrapped.put("error", wrapError(exception));
                        }
                        reply.reply(wrapped);
                    });
                } else {
                    channel.setMessageHandler(null);
                }
            }
            {
                BasicMessageChannel<Object> channel =
                        new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.ConnectionControl.connectToWatch", new StandardMessageCodec());
                if (api != null) {
                    channel.setMessageHandler((message, reply) -> {
                        HashMap<String, HashMap> wrapped = new HashMap<>();
                        try {
                            @SuppressWarnings("ConstantConditions")
                            NumberWrapper input = NumberWrapper.fromMap((HashMap) message);
                            api.connectToWatch(input);
                            wrapped.put("result", null);
                        } catch (Exception exception) {
                            wrapped.put("error", wrapError(exception));
                        }
                        reply.reply(wrapped);
                    });
                } else {
                    channel.setMessageHandler(null);
                }
            }
            {
                BasicMessageChannel<Object> channel =
                        new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.ConnectionControl.sendRawPacket", new StandardMessageCodec());
                if (api != null) {
                    channel.setMessageHandler((message, reply) -> {
                        HashMap<String, HashMap> wrapped = new HashMap<>();
                        try {
                            @SuppressWarnings("ConstantConditions")
                            ListWrapper input = ListWrapper.fromMap((HashMap) message);
                            api.sendRawPacket(input);
                            wrapped.put("result", null);
                        } catch (Exception exception) {
                            wrapped.put("error", wrapError(exception));
                        }
                        reply.reply(wrapped);
                    });
                } else {
                    channel.setMessageHandler(null);
                }
            }
        }
    }

    private static HashMap wrapError(Exception exception) {
        HashMap<String, Object> errorMap = new HashMap<>();
        errorMap.put("message", exception.toString());
        errorMap.put("code", null);
        errorMap.put("details", null);
        return errorMap;
    }
}
