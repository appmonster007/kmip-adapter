package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.Comment;

public class CommentTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Comment, Comment.CommentBuilder> {

  public CommentTtlvDeserializer() {
    super(Comment.kmipTag, Comment.encodingType);
  }

  @Override
  protected Comment.CommentBuilder createBuilder() {
    return Comment.builder();
  }

  @Override
  protected void setValue(Comment.CommentBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected Comment build(Comment.CommentBuilder builder) {
    return builder.build();
  }
}