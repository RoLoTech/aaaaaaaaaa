package labTic.services.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRestaurant is a Querydsl query type for Restaurant
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRestaurant extends EntityPathBase<Restaurant> {

    private static final long serialVersionUID = 78100775L;

    public static final QRestaurant restaurant = new QRestaurant("restaurant");

    public final StringPath address = createString("address");

    public final StringPath area = createString("area");

    public final NumberPath<Integer> capacity = createNumber("capacity", Integer.class);

    public final NumberPath<Integer> completedReservations = createNumber("completedReservations", Integer.class);

    public final StringPath foodtype = createString("foodtype");

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final StringPath priceRange = createString("priceRange");

    public final NumberPath<Float> rating = createNumber("rating", Float.class);

    public final NumberPath<Long> rut = createNumber("rut", Long.class);

    public final StringPath style = createString("style");

    public final BooleanPath availability = createBoolean("availability");

    public final ArrayPath<boolean[], Boolean> tables = createArray("tables", boolean[].class);

    public QRestaurant(String variable) {
        super(Restaurant.class, forVariable(variable));
    }

    public QRestaurant(Path<? extends Restaurant> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRestaurant(PathMetadata metadata) {
        super(Restaurant.class, metadata);
    }

}

