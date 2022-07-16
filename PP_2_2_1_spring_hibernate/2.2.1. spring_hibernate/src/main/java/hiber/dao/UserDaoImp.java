package hiber.dao;

import hiber.model.User;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImp implements UserDao {
   @Autowired
   private SessionFactory sessionFactory;

   public UserDaoImp() {
   }

   public void add(User user) {
      sessionFactory.getCurrentSession().save(user.getCar());
      sessionFactory.getCurrentSession().save(user);
   }

   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public User findUserByCarModelAndSeries(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery
              ("from User us where us.car.model =:model AND us.car.series =:series")
              .setParameter("model", model).setParameter("series", series);
      return (User)query.getSingleResult();
   }
}

