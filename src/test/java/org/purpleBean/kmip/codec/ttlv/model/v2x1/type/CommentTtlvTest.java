package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.Comment;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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