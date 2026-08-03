package org.purplebean.kmip.test.suite;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.BaseKmipTest;

/**
 * Reusable JSON serialization/deserialization test suite for KMIP objects.
 * Extend this class and implement factory methods to cover a specific type.
 */
@DisplayName("Abstract JSON Serialization Suite")
public abstract class AbstractJsonSerializationTestSuite<T> extends BaseKmipTest
    implements KmipSerializationTestSuite<T, ObjectMapper, String> {

  @Override
  public ObjectMapper getMapper() {
    return getJsonMapper();
  }

  @Override
  public String serialize(T object) throws Exception {
    return getMapper().writeValueAsString(object);
  }

  @Override
  public T deserialize(String serialized) throws Exception {
    return getMapper().readValue(serialized, type());
  }

  @Override
  public void withKmipSpec(KmipSpec spec, Runnable operation) {
    super.withKmipSpec(spec, operation);
  }
}
