package com.emotioncity.soriento

import com.orientechnologies.orient.core.record.impl.ODocument
import com.emotioncity.soriento.RichODocumentImpl._

/**
 * Created by stream on 07.07.15.
 */
case class Simple(sField: String)

object Simple {

  implicit object SimpleReader extends ODocumentReader[Simple] {

    def read(oDocument: ODocument): Simple = {
      println("sField in SimpleReader: " + oDocument.fieldType("sField") + " : " + oDocument.field("sField"))
      Simple(
        oDocument.get[String]("sField").get //TODO stub
      )
    }
  }

}