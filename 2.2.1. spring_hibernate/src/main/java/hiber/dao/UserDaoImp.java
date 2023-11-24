package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserByCar(Car car) {
        String hql = """
                FROM User u
                WHERE u.car.model = :carModel
                AND u.car.series = :carSeries
                """;
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("carModel", car.getModel());
        query.setParameter("carSeries", car.getSeries());

        return query.getSingleResult();
    }
}
