package com.bajidan.ingrydspringboot_advanceuser.repository;

import com.bajidan.ingrydspringboot_advanceuser.model.AdvancedUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CriteriaUserRepository {

    private final EntityManager em;

    public List<AdvancedUser> getAllUsers() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<AdvancedUser> criteriaQuery = criteriaBuilder.createQuery(AdvancedUser.class);

        // SELECT * FROM advancedUser
        Root<AdvancedUser> root = criteriaQuery.from(AdvancedUser.class);
        criteriaQuery.select(root);

        TypedQuery<AdvancedUser> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

    public AdvancedUser getUserById(Long id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<AdvancedUser> criteriaQuery = criteriaBuilder.createQuery(AdvancedUser.class);

        // SELECT * FROM advancedUser
        Root<AdvancedUser> root = criteriaQuery.from(AdvancedUser.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
        return em.createQuery(criteriaQuery).getSingleResult();
    }

    public List<AdvancedUser> search(String name, String gender) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<AdvancedUser> criteriaQuery = criteriaBuilder.createQuery(AdvancedUser.class);

        Root<AdvancedUser> root = criteriaQuery.from(AdvancedUser.class);

        Predicate searchName = criteriaBuilder.like(root.get("fullName"), name + "%");
        Predicate searchGender = criteriaBuilder.equal(root.get("gender"), gender);
        Predicate nameOrGender = criteriaBuilder.or(searchName, searchGender);

        criteriaQuery.where(nameOrGender);

        TypedQuery<AdvancedUser> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
