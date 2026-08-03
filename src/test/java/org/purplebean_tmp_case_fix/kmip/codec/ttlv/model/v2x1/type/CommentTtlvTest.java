package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.Comment;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Comment Ttlv Serialization Tests")
class CommentTtlvTest extends AbstractTtlvSerializationTestSuite<Comment> {

  @Override
  public Class<Comment> type() {
    return Comment.class;
  }

  @Override
  public Comment createDefault() {
    return Comment.of("default-string");
  }

  @Override
  public Comment createVariant() {
    return Comment.of("variant-string");
  }
}