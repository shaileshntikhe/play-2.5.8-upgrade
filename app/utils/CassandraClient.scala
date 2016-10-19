package utils

import com.datastax.driver.core.Cluster
import play.api.Play.current

import scala.concurrent.ExecutionContext
import java.util.concurrent.Executor

import com.datastax.driver.core.policies.ConstantReconnectionPolicy
import play.libs.Akka

import scala.collection.JavaConverters._


object CassandraClient {

  private val nodes = current.configuration.getStringList("CASSANDRA_ENDPOINT").map(_.asScala.toList).getOrElse(List("127.0.0.1"))

  private val cluster = Cluster.builder.addContactPoints(nodes: _*).withReconnectionPolicy(new ConstantReconnectionPolicy(1000L)).build

  private val keySpace = current.configuration.getString("CASSANDRA_KEYSPACE").getOrElse("dms")

  val session = cluster.connect(keySpace)
  
  implicit val ec: ExecutionContext = Akka.system.dispatchers.lookup("contexts.cassandra-pool")
}