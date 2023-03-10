package io.ino.solrs

import org.apache.solr.client.solrj.request.QueryRequest
import org.apache.solr.common.params.SolrParams
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers.hasProperty
import org.mockito.ArgumentMatcher
import org.mockito.ArgumentMatchers.argThat
import org.mockito.hamcrest.MockitoHamcrest

object SolrMatchers {

  def hasQuery(query: SolrParams): QueryRequest =
    MockitoHamcrest.argThat(hasProperty("params", equalTo(query)))

  def hasBaseUrlOf(solrServer: SolrServer): SolrServer =
    argThat(new ArgumentMatcher[SolrServer] {
      override def matches(argument: SolrServer): Boolean = argument match {
        case server: SolrServer => server.baseUrl == solrServer.baseUrl
        case _                  => false
      }
    })

}
