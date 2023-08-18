package com.augustnagro.magnum

import scala.reflect.ClassTag
import scala.deriving.Mirror

/** Factory for Repo default methods */
trait DbType:
  def buildRepoDefaults[EC, E, ID, TI](
      tableNameSql: String,
      eElemNames: Seq[String],
      eElemNamesSql: Seq[String],
      eElemCodecs: Seq[DbCodec[?]],
      ecElemNames: Seq[String],
      ecElemNamesSql: Seq[String],
      idIndex: Int,
      ti: TI
  )(using
      eCodec: DbCodec[E],
      ecCodec: DbCodec[EC],
      idCodec: DbCodec[ID],
      eClassTag: ClassTag[E],
      ecClassTag: ClassTag[EC],
      idClassTag: ClassTag[ID]
  ): RepoDefaults[EC, E, ID] { type TableInfoType = TI }
