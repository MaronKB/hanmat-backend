package com.human.hanmat.convert;

import com.human.hanmat.entity.Restaurant;
import com.human.hanmat.entity.RestaurantTest;
import com.human.hanmat.service.RestaurantService;
import org.locationtech.proj4j.BasicCoordinateTransform;
import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.ProjCoordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoConvert {
    @Autowired
    private RestaurantService restaurantService;

    public void convertGRStoWGS84() {
        List<Restaurant> restaurants = restaurantService.findAll();
        for (Restaurant restaurant: restaurants) {
            ProjCoordinate target = getProjCoordinate(restaurant);

            restaurant.setX(target.x);
            restaurant.setY(target.y);

            restaurantService.update(restaurant);
        }
        System.out.println("Conversion completed");
    }

    private static ProjCoordinate getProjCoordinate(Restaurant restaurant) {
        double x = restaurant.getX();
        double y = restaurant.getY();

        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem grs80 = crsFactory.createFromName("epsg:2097");
        CoordinateReferenceSystem wgs84 = crsFactory.createFromName("epsg:4326");
        BasicCoordinateTransform transform = new BasicCoordinateTransform(grs80, wgs84);

        ProjCoordinate source = new ProjCoordinate(x, y);
        ProjCoordinate target = new ProjCoordinate();

        transform.transform(source, target);
        return target;
    }

    public void convert() {
        convertGRStoWGS84();
    }
}
