package repository.base;

import entity.KhachHang;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateConfig;

import java.util.List;

public abstract class AbstractRepository<EntityType> {
    private final Class<EntityType> entityClass;

    public AbstractRepository(Class<EntityType> entityClass) {
        this.entityClass = entityClass;
    }

    public List<EntityType> paging(boolean all, Integer firstRes, Integer maxRes) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Query<EntityType> query = session.createQuery("FROM " + entityClass.getName(), entityClass);

            if (!all) {
                query.setFirstResult(firstRes);
                query.setMaxResults(maxRes);
            }

            return query.list();
        }
    }

    public List<EntityType> getAll() {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Query<EntityType> query = session.createQuery("FROM " + entityClass.getName(), entityClass);
            return query.list();
        }
    }

    public EntityType getOne(Object id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            return session.find(entityClass, id);
        }
    }

    public EntityType add(EntityType entity) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public EntityType update(EntityType entity) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Object id) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            EntityType entity = session.find(entityClass, id);
            if (entity != null) {
                session.delete(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

}
