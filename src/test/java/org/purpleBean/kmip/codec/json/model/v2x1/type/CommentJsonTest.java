package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.Comment;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Comment Json Serialization Tests")
class CommentJsonTest extends AbstractJsonSerializationTestSuite<Comment> {

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