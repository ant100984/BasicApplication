package it.aesposito.jstore.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FattureExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fatture
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fatture
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fatture
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fatture
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public FattureExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fatture
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fatture
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fatture
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fatture
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fatture
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fatture
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fatture
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
     * This method corresponds to the database table fatture
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
     * This method corresponds to the database table fatture
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fatture
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
     * This class corresponds to the database table fatture
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

        public Criteria andNumeroIsNull() {
            addCriterion("numero is null");
            return (Criteria) this;
        }

        public Criteria andNumeroIsNotNull() {
            addCriterion("numero is not null");
            return (Criteria) this;
        }

        public Criteria andNumeroEqualTo(Integer value) {
            addCriterion("numero =", value, "numero");
            return (Criteria) this;
        }

        public Criteria andNumeroNotEqualTo(Integer value) {
            addCriterion("numero <>", value, "numero");
            return (Criteria) this;
        }

        public Criteria andNumeroGreaterThan(Integer value) {
            addCriterion("numero >", value, "numero");
            return (Criteria) this;
        }

        public Criteria andNumeroGreaterThanOrEqualTo(Integer value) {
            addCriterion("numero >=", value, "numero");
            return (Criteria) this;
        }

        public Criteria andNumeroLessThan(Integer value) {
            addCriterion("numero <", value, "numero");
            return (Criteria) this;
        }

        public Criteria andNumeroLessThanOrEqualTo(Integer value) {
            addCriterion("numero <=", value, "numero");
            return (Criteria) this;
        }

        public Criteria andNumeroIn(List<Integer> values) {
            addCriterion("numero in", values, "numero");
            return (Criteria) this;
        }

        public Criteria andNumeroNotIn(List<Integer> values) {
            addCriterion("numero not in", values, "numero");
            return (Criteria) this;
        }

        public Criteria andNumeroBetween(Integer value1, Integer value2) {
            addCriterion("numero between", value1, value2, "numero");
            return (Criteria) this;
        }

        public Criteria andNumeroNotBetween(Integer value1, Integer value2) {
            addCriterion("numero not between", value1, value2, "numero");
            return (Criteria) this;
        }

        public Criteria andAnnoIsNull() {
            addCriterion("anno is null");
            return (Criteria) this;
        }

        public Criteria andAnnoIsNotNull() {
            addCriterion("anno is not null");
            return (Criteria) this;
        }

        public Criteria andAnnoEqualTo(Integer value) {
            addCriterion("anno =", value, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoNotEqualTo(Integer value) {
            addCriterion("anno <>", value, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoGreaterThan(Integer value) {
            addCriterion("anno >", value, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("anno >=", value, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoLessThan(Integer value) {
            addCriterion("anno <", value, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoLessThanOrEqualTo(Integer value) {
            addCriterion("anno <=", value, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoIn(List<Integer> values) {
            addCriterion("anno in", values, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoNotIn(List<Integer> values) {
            addCriterion("anno not in", values, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoBetween(Integer value1, Integer value2) {
            addCriterion("anno between", value1, value2, "anno");
            return (Criteria) this;
        }

        public Criteria andAnnoNotBetween(Integer value1, Integer value2) {
            addCriterion("anno not between", value1, value2, "anno");
            return (Criteria) this;
        }

        public Criteria andTotaleIsNull() {
            addCriterion("totale is null");
            return (Criteria) this;
        }

        public Criteria andTotaleIsNotNull() {
            addCriterion("totale is not null");
            return (Criteria) this;
        }

        public Criteria andTotaleEqualTo(Double value) {
            addCriterion("totale =", value, "totale");
            return (Criteria) this;
        }

        public Criteria andTotaleNotEqualTo(Double value) {
            addCriterion("totale <>", value, "totale");
            return (Criteria) this;
        }

        public Criteria andTotaleGreaterThan(Double value) {
            addCriterion("totale >", value, "totale");
            return (Criteria) this;
        }

        public Criteria andTotaleGreaterThanOrEqualTo(Double value) {
            addCriterion("totale >=", value, "totale");
            return (Criteria) this;
        }

        public Criteria andTotaleLessThan(Double value) {
            addCriterion("totale <", value, "totale");
            return (Criteria) this;
        }

        public Criteria andTotaleLessThanOrEqualTo(Double value) {
            addCriterion("totale <=", value, "totale");
            return (Criteria) this;
        }

        public Criteria andTotaleIn(List<Double> values) {
            addCriterion("totale in", values, "totale");
            return (Criteria) this;
        }

        public Criteria andTotaleNotIn(List<Double> values) {
            addCriterion("totale not in", values, "totale");
            return (Criteria) this;
        }

        public Criteria andTotaleBetween(Double value1, Double value2) {
            addCriterion("totale between", value1, value2, "totale");
            return (Criteria) this;
        }

        public Criteria andTotaleNotBetween(Double value1, Double value2) {
            addCriterion("totale not between", value1, value2, "totale");
            return (Criteria) this;
        }

        public Criteria andImponibileIsNull() {
            addCriterion("imponibile is null");
            return (Criteria) this;
        }

        public Criteria andImponibileIsNotNull() {
            addCriterion("imponibile is not null");
            return (Criteria) this;
        }

        public Criteria andImponibileEqualTo(Double value) {
            addCriterion("imponibile =", value, "imponibile");
            return (Criteria) this;
        }

        public Criteria andImponibileNotEqualTo(Double value) {
            addCriterion("imponibile <>", value, "imponibile");
            return (Criteria) this;
        }

        public Criteria andImponibileGreaterThan(Double value) {
            addCriterion("imponibile >", value, "imponibile");
            return (Criteria) this;
        }

        public Criteria andImponibileGreaterThanOrEqualTo(Double value) {
            addCriterion("imponibile >=", value, "imponibile");
            return (Criteria) this;
        }

        public Criteria andImponibileLessThan(Double value) {
            addCriterion("imponibile <", value, "imponibile");
            return (Criteria) this;
        }

        public Criteria andImponibileLessThanOrEqualTo(Double value) {
            addCriterion("imponibile <=", value, "imponibile");
            return (Criteria) this;
        }

        public Criteria andImponibileIn(List<Double> values) {
            addCriterion("imponibile in", values, "imponibile");
            return (Criteria) this;
        }

        public Criteria andImponibileNotIn(List<Double> values) {
            addCriterion("imponibile not in", values, "imponibile");
            return (Criteria) this;
        }

        public Criteria andImponibileBetween(Double value1, Double value2) {
            addCriterion("imponibile between", value1, value2, "imponibile");
            return (Criteria) this;
        }

        public Criteria andImponibileNotBetween(Double value1, Double value2) {
            addCriterion("imponibile not between", value1, value2, "imponibile");
            return (Criteria) this;
        }

        public Criteria andDtmEmissioneIsNull() {
            addCriterion("dtm_emissione is null");
            return (Criteria) this;
        }

        public Criteria andDtmEmissioneIsNotNull() {
            addCriterion("dtm_emissione is not null");
            return (Criteria) this;
        }

        public Criteria andDtmEmissioneEqualTo(Date value) {
            addCriterion("dtm_emissione =", value, "dtmEmissione");
            return (Criteria) this;
        }

        public Criteria andDtmEmissioneNotEqualTo(Date value) {
            addCriterion("dtm_emissione <>", value, "dtmEmissione");
            return (Criteria) this;
        }

        public Criteria andDtmEmissioneGreaterThan(Date value) {
            addCriterion("dtm_emissione >", value, "dtmEmissione");
            return (Criteria) this;
        }

        public Criteria andDtmEmissioneGreaterThanOrEqualTo(Date value) {
            addCriterion("dtm_emissione >=", value, "dtmEmissione");
            return (Criteria) this;
        }

        public Criteria andDtmEmissioneLessThan(Date value) {
            addCriterion("dtm_emissione <", value, "dtmEmissione");
            return (Criteria) this;
        }

        public Criteria andDtmEmissioneLessThanOrEqualTo(Date value) {
            addCriterion("dtm_emissione <=", value, "dtmEmissione");
            return (Criteria) this;
        }

        public Criteria andDtmEmissioneIn(List<Date> values) {
            addCriterion("dtm_emissione in", values, "dtmEmissione");
            return (Criteria) this;
        }

        public Criteria andDtmEmissioneNotIn(List<Date> values) {
            addCriterion("dtm_emissione not in", values, "dtmEmissione");
            return (Criteria) this;
        }

        public Criteria andDtmEmissioneBetween(Date value1, Date value2) {
            addCriterion("dtm_emissione between", value1, value2, "dtmEmissione");
            return (Criteria) this;
        }

        public Criteria andDtmEmissioneNotBetween(Date value1, Date value2) {
            addCriterion("dtm_emissione not between", value1, value2, "dtmEmissione");
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

        public Criteria andDtmModificaIsNull() {
            addCriterion("dtm_modifica is null");
            return (Criteria) this;
        }

        public Criteria andDtmModificaIsNotNull() {
            addCriterion("dtm_modifica is not null");
            return (Criteria) this;
        }

        public Criteria andDtmModificaEqualTo(Date value) {
            addCriterion("dtm_modifica =", value, "dtmModifica");
            return (Criteria) this;
        }

        public Criteria andDtmModificaNotEqualTo(Date value) {
            addCriterion("dtm_modifica <>", value, "dtmModifica");
            return (Criteria) this;
        }

        public Criteria andDtmModificaGreaterThan(Date value) {
            addCriterion("dtm_modifica >", value, "dtmModifica");
            return (Criteria) this;
        }

        public Criteria andDtmModificaGreaterThanOrEqualTo(Date value) {
            addCriterion("dtm_modifica >=", value, "dtmModifica");
            return (Criteria) this;
        }

        public Criteria andDtmModificaLessThan(Date value) {
            addCriterion("dtm_modifica <", value, "dtmModifica");
            return (Criteria) this;
        }

        public Criteria andDtmModificaLessThanOrEqualTo(Date value) {
            addCriterion("dtm_modifica <=", value, "dtmModifica");
            return (Criteria) this;
        }

        public Criteria andDtmModificaIn(List<Date> values) {
            addCriterion("dtm_modifica in", values, "dtmModifica");
            return (Criteria) this;
        }

        public Criteria andDtmModificaNotIn(List<Date> values) {
            addCriterion("dtm_modifica not in", values, "dtmModifica");
            return (Criteria) this;
        }

        public Criteria andDtmModificaBetween(Date value1, Date value2) {
            addCriterion("dtm_modifica between", value1, value2, "dtmModifica");
            return (Criteria) this;
        }

        public Criteria andDtmModificaNotBetween(Date value1, Date value2) {
            addCriterion("dtm_modifica not between", value1, value2, "dtmModifica");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fatture
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
     * This class corresponds to the database table fatture
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