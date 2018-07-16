package io.ino.solrs

import org.apache.solr.common.cloud.Replica
import org.apache.solr.common.cloud.ZkStateReader

object Fixtures {

  def shardReplica(baseUrl: String, status: ServerStatus = Enabled, isLeader: Boolean): ShardReplica = {
    import scala.collection.JavaConverters.mapAsJavaMapConverter
    val leaderProps: Map[String, AnyRef] = if(isLeader) Map(ZkStateReader.LEADER_PROP -> true.toString) else Map.empty
    val replicaStatus = status match {
      case Enabled => Replica.State.ACTIVE
      case Disabled => Replica.State.RECOVERING
      case Failed => Replica.State.RECOVERY_FAILED
    }
    val replica = new Replica(baseUrl, (Map[String, AnyRef](
      ZkStateReader.STATE_PROP -> replicaStatus.toString
    ) ++ leaderProps).asJava)
    ShardReplica(baseUrl, replica)
  }

}