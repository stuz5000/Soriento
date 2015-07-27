Soriento
========

## Scala to OrientDb object mapping library

Soriento is an object-relational mapping framework from scala case classes to OrientDb ODocument.
##News
 - Update documents with field annotated as javax.persistent.Id
 - Bug fixes
 - <strong>Warning:</strong> deserialization mechanism is broken in this commit

## Features

 - Creating/deleting OrientDb classes by case classes.
 - Transparent CRUD for documents represented as case classes.
 - Linked or Embedded definitions for case classes.
 - Support serialization/deserialization for case classes with @Embedded list/set of case classes.
 - Support OType mapping Scala => OrientDb OTypes.
 - Transactions support.
 - Query by SQL.
 
Supported types:
- Long, Double, Int, Float, Short, String, case class.

##Coming soon
- LinkList, LinkSet, LinkMap.
- EmbeddedMap.

##Add to you project
```scala
 lazy val youProject = Project("YouProject", file("."))
  .settings(commonSettings: _*)
  .dependsOn(sorientoProject)
  
 lazy val sorientoProject = RootProject(uri("https://github.com/dimparf/Soriento.git#master"))
```

##Usage
Simple example:
```scala
  import com.emotioncity.Dsl._ // or extends Dsl trait
  
  implicit val orientDb: ODatabaseDocumentTx = ???

  case class Message(content: String)
  case class Blog(author: String, @Embedded message: Message) // or @Linked
  case class BlogWithEmbeddedMessages(author: String, @EmbeddedSet messages: List[Message])
  
  //schema-full (use com.emotioncity.soriento.ODb trait) mode or without this lines - schema less
  createOClass[Message] 
  createOClass[Blog]
  createOClass[BlogWithEmbeddedMessages]
  
  val blog = Blog("Dim", message = Message("Hi")) //or without named params Blog("Dim", Message("Hi))
  val blogWithEmbeddedMessages = BlogWithEmbeddedMessages("John", List(Message("Hi"), Message("New blog note")))
  //ActiveRecord style
  blog.save
  blogWithEmbeddedMessages.save
  
  //..or
  orientDb.save(blog)
  
  
  val blogs: List[Blog] = db.queryBySql[Blog]("select from blog")
    
  deleteOClass[Message]
  deleteOClass[Blog]
  deleteOClass[BlogWithEmbeddedMessages]
```

More examples in test directory.

## Testing
To run unit tests:

    sbt test

## Contributing

Welcome to contribute!
You can always post an issue or (even better) fork the project, implement your idea or fix the bug you have found and send a pull request. 
Just remember to test it when you are done. Here is how:

Run sbt test to run and compile tests.
    
## License
This software is available under the [Apache License, Version 2.0](LICENSE).    
