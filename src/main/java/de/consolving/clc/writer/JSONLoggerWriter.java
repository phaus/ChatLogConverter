package de.consolving.clc.writer;

import de.consolving.clc.model.Account;
import de.consolving.clc.model.Chat;
import de.consolving.clc.model.Contact;
import de.consolving.clc.model.Entry;

/**
 * This Class Logges all Chats as JSON.
 *
 * @author Philipp Haußleiter
 */
public class JSONLoggerWriter implements ChatLogWriter {

    public void openAccount(Account account) {
        System.out.println("{");
        System.out.println("\t\"name\":\"" + account.getName() + "\",");
        System.out.println("\t\"protocol\":\"" + account.getProtocol() + "\",");
        System.out.println("\t\"contacts\":[");
    }

    public void openContact(Contact contact) {
        System.out.println("\t\t{");
        System.out.println("\t\t\t\"chats\":[");
    }

    public void openChat(Chat chat) {
        System.out.println("\t\t\t\t{");
        System.out.println("\t\t\t\t\t\"account\":\"" + chat.getAccount() + "\",");
        System.out.println("\t\t\t\t\t\"entries\":[");
    }

    public void writerEntry(Entry entry, Chat chat) {
        System.out.println("\t\t\t\t\t\t{"
                + "\"id\":\"" + entry.getId() + "\","
                + "\"name\":\"" + entry.getName() + "\","
                + "\"time\":\"" + entry.getTime() + "\","
                + "\"type\":\"" + entry.getType() + "\","
                + "\"message\":\"" + quote(entry.getMessage()) + "\""
                + "},");

    }

    public void closeChat(Chat chat) {
        System.out.println("\t\t\t\t\t\t{}");
        System.out.println("\t\t\t\t\t]");
        System.out.println("\t\t\t\t},");
    }

    public void closeContact(Contact contact) {
        System.out.println("\t\t\t\t{}");
        System.out.println("\t\t\t]");
        System.out.println("\t\t},");
    }

    public void closeAccount(Account account) {
        System.out.println("\t\t{}");
        System.out.println("\t]");
        System.out.println("}");
    }

    private static String quote(String string) {
        if (string == null || string.length() == 0) {
            return "";
        }

        char c = 0;
        int i;
        int len = string.length();
        StringBuilder sb = new StringBuilder(len + 4);
        String t;
        for (i = 0; i < len; i += 1) {
            c = string.charAt(i);
            switch (c) {
                case '\\':
                case '"':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '/':
                    //                if (b == '<') {
                    sb.append('\\');
                    //                }
                    sb.append(c);
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    if (c < ' ') {
                        t = "000" + Integer.toHexString(c);
                        sb.append("\\u").append(t.substring(t.length() - 4));
                    } else {
                        sb.append(c);
                    }
            }
        }
        return sb.toString();
    }
}
