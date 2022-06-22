package be.bf.banque.utils;

public class Config {
    public static class Db {
        private static final String DB_TYPE = "sqlite";
        private static final String DB_PATH = Db.class.getClassLoader().getResource("banque.sqlite3").toString();

        public static String getUrl() {
            StringBuilder builder = new StringBuilder();

            builder.append("jdbc:").append(DB_TYPE).append(":");
            builder.append(DB_PATH);

            return builder.toString();
        }
    }
}
