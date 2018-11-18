package labTic.business.entities;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QTables is a Querydsl query type for Tables
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTable extends EntityPathBase<Tables> {

    private static final long serialVersionUID = 2017978037L;

    public static final QTable table = new QTable("table");



//    private LocalTime startReservation;

    public final StringPath occupant = createString("occupant");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> restaurantRut = createNumber("restaurantRut", Long.class);

    public final NumberPath<Integer> capacity = createNumber("capacity", Integer.class);


    public QTable(String variable) {
        super(Tables.class, forVariable(variable));
    }

    public QTable(Path<? extends Tables> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTable(PathMetadata metadata) {
        super(Tables.class, metadata);
    }

}

