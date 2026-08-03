package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.Comment;

public class CommentBenchmarkSubject extends KmipBenchmarkSubject<Comment> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public CommentBenchmarkSubject() throws Exception {
    Comment subject = Comment.of("default-string");
    initialize(subject, Comment.class);
  }

  @Override
  public String name() {
    return "Comment";
  }
}