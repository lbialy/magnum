import com.augustnagro.magnum.*
import munit.FunSuite

class RepoTests extends FunSuite:

  case class PersonCreator(
      firstName: Option[String],
      lastName: String,
      isAdmin: Boolean
  ) derives DbCodec

  @Table(SqliteDbType, SqlNameMapper.CamelToSnakeCase)
  case class Person(
      id: Long,
      firstName: Option[String],
      lastName: String,
      isAdmin: Boolean,
      created: String
  ) derives DbCodec

  class PersonRepo extends Repo[PersonCreator, Person, Long]:
    def customQueryUsingTable(using DbCon): Vector[Person] =
      sql"SELECT ${table.firstName} FROM ${table}".query[Person].run()

    val tblInfo = TableInfo[PersonCreator, Person, Long]

    def customQueryUsingTableInfo(using DbCon): Vector[Person] =
      sql"SELECT ${tblInfo.firstName} FROM ${tblInfo}".query[Person].run()

end RepoTests
