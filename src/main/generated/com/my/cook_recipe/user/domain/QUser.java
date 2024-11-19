package com.my.cook_recipe.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1213822416L;

    public static final QUser user = new QUser("user");

    public final com.my.cook_recipe.common.entity.QBaseEntity _super = new com.my.cook_recipe.common.entity.QBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDateTime = _super.lastModifiedDateTime;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> localDateTime = _super.localDateTime;

    //inherited
    public final StringPath modifier = _super.modifier;

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath userId = createString("userId");

    //inherited
    public final StringPath writer = _super.writer;

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

