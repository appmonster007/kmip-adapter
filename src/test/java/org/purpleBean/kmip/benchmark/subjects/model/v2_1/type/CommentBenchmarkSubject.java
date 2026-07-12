package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.Comment;

public class CommentBenchmarkSubject extends KmipBenchmarkSubject<Comment> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public CommentBenchmarkSubject() throws Exception {
        Comment subject = Comment.of("default-string");
        initialize(subject, Comment.class);
    }

    @Override
    public String name() {
        return "Comment";
    }
}