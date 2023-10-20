import 'package:postgres/postgres.dart';

class SQLInstance {
  static Future<PostgreSQLResult> executeQuerry({String query = ""}) async {
    var databaseConnection = PostgreSQLConnection(
      "mahmud.db.elephantsql.com",
      5432,
      "utmvlkky",
      queryTimeoutInSeconds: 3600,
      timeoutInSeconds: 3600,
      username: "utmvlkky",
      password: "1rIHiIXPuyZDM7eXR_Z2NmvsF7sC6Kb4",
    );

    await databaseConnection.open();
    final results = await databaseConnection.query(query);
    await databaseConnection.close();
    return results;
  }
}
