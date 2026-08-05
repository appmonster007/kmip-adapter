package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.QueryFunction;

/**
 * Benchmark subject for {@link QueryFunction}.
 */
public class QueryFunctionBenchmarkSubject extends KmipBenchmarkSubject<QueryFunction> {

  /**
   * Constructs a new {@link QueryFunctionBenchmarkSubject}.
   */
  public QueryFunctionBenchmarkSubject() throws Exception {
    QueryFunction queryFunction = QueryFunction.Standard.QUERY_SERVER_INFORMATION.inst();
    initialize(queryFunction, QueryFunction.class);
  }

  @Override
  public String name() {
    return "QueryFunction";
  }

}
