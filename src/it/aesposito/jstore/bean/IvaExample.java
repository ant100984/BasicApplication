package it.aesposito.jstore.bean;

import java.util.ArrayList;
import java.util.List;

public class IvaExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public IvaExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDescrizioneIsNull() {
            addCriterion("descrizione is null");
            return (Criteria) this;
        }

        public Criteria andDescrizioneIsNotNull() {
            addCriterion("descrizione is not null");
            return (Criteria) this;
        }

        public Criteria andDescrizioneEqualTo(String value) {
            addCriterion("descrizione =", value, "descrizione");
            return (Criteria) this;
        }

        public Criteria andDescrizioneNotEqualTo(String value) {
            addCriterion("descrizione <>", value, "descrizione");
            return (Criteria) this;
        }

        public Criteria andDescrizioneGreaterThan(String value) {
            addCriterion("descrizione >", value, "descrizione");
            return (Criteria) this;
        }

        public Criteria andDescrizioneGreaterThanOrEqualTo(String value) {
            addCriterion("descrizione >=", value, "descrizione");
            return (Criteria) this;
        }

        public Criteria andDescrizioneLessThan(String value) {
            addCriterion("descrizione <", value, "descrizione");
            return (Criteria) this;
        }

        public Criteria andDescrizioneLessThanOrEqualTo(String value) {
            addCriterion("descrizione <=", value, "descrizione");
            return (Criteria) this;
        }

        public Criteria andDescrizioneLike(String value) {
            addCriterion("descrizione like", value, "descrizione");
            return (Criteria) this;
        }

        public Criteria andDescrizioneNotLike(String value) {
            addCriterion("descrizione not like", value, "descrizione");
            return (Criteria) this;
        }

        public Criteria andDescrizioneIn(List<String> values) {
            addCriterion("descrizione in", values, "descrizione");
            return (Criteria) this;
        }

        public Criteria andDescrizioneNotIn(List<String> values) {
            addCriterion("descrizione not in", values, "descrizione");
            return (Criteria) this;
        }

        public Criteria andDescrizioneBetween(String value1, String value2) {
            addCriterion("descrizione between", value1, value2, "descrizione");
            return (Criteria) this;
        }

        public Criteria andDescrizioneNotBetween(String value1, String value2) {
            addCriterion("descrizione not between", value1, value2, "descrizione");
            return (Criteria) this;
        }

        public Criteria andIvaIsNull() {
            addCriterion("iva is null");
            return (Criteria) this;
        }

        public Criteria andIvaIsNotNull() {
            addCriterion("iva is not null");
            return (Criteria) this;
        }

        public Criteria andIvaEqualTo(Double value) {
            addCriterion("iva =", value, "iva");
            return (Criteria) this;
        }

        public Criteria andIvaNotEqualTo(Double value) {
            addCriterion("iva <>", value, "iva");
            return (Criteria) this;
        }

        public Criteria andIvaGreaterThan(Double value) {
            addCriterion("iva >", value, "iva");
            return (Criteria) this;
        }

        public Criteria andIvaGreaterThanOrEqualTo(Double value) {
            addCriterion("iva >=", value, "iva");
            return (Criteria) this;
        }

        public Criteria andIvaLessThan(Double value) {
            addCriterion("iva <", value, "iva");
            return (Criteria) this;
        }

        public Criteria andIvaLessThanOrEqualTo(Double value) {
            addCriterion("iva <=", value, "iva");
            return (Criteria) this;
        }

        public Criteria andIvaIn(List<Double> values) {
            addCriterion("iva in", values, "iva");
            return (Criteria) this;
        }

        public Criteria andIvaNotIn(List<Double> values) {
            addCriterion("iva not in", values, "iva");
            return (Criteria) this;
        }

        public Criteria andIvaBetween(Double value1, Double value2) {
            addCriterion("iva between", value1, value2, "iva");
            return (Criteria) this;
        }

        public Criteria andIvaNotBetween(Double value1, Double value2) {
            addCriterion("iva not between", value1, value2, "iva");
            return (Criteria) this;
        }

        public Criteria andDescrizioneLikeInsensitive(String value) {
            addCriterion("upper(descrizione) like", value.toUpperCase(), "descrizione");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table iva
     *
     * @mbggenerated do_not_delete_during_merge Wed Mar 26 22:23:47 CET 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table iva
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}