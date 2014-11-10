package it.aesposito.jstore.bean;

import java.util.ArrayList;
import java.util.List;

public class ArticoloFatturaExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table articolo_fattura
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table articolo_fattura
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table articolo_fattura
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table articolo_fattura
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public ArticoloFatturaExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table articolo_fattura
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table articolo_fattura
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table articolo_fattura
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table articolo_fattura
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table articolo_fattura
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table articolo_fattura
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table articolo_fattura
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
     * This method corresponds to the database table articolo_fattura
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
     * This method corresponds to the database table articolo_fattura
     *
     * @mbggenerated Wed Mar 26 22:23:47 CET 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table articolo_fattura
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
     * This class corresponds to the database table articolo_fattura
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

        public Criteria andArticoloIsNull() {
            addCriterion("articolo is null");
            return (Criteria) this;
        }

        public Criteria andArticoloIsNotNull() {
            addCriterion("articolo is not null");
            return (Criteria) this;
        }

        public Criteria andArticoloEqualTo(Integer value) {
            addCriterion("articolo =", value, "articolo");
            return (Criteria) this;
        }

        public Criteria andArticoloNotEqualTo(Integer value) {
            addCriterion("articolo <>", value, "articolo");
            return (Criteria) this;
        }

        public Criteria andArticoloGreaterThan(Integer value) {
            addCriterion("articolo >", value, "articolo");
            return (Criteria) this;
        }

        public Criteria andArticoloGreaterThanOrEqualTo(Integer value) {
            addCriterion("articolo >=", value, "articolo");
            return (Criteria) this;
        }

        public Criteria andArticoloLessThan(Integer value) {
            addCriterion("articolo <", value, "articolo");
            return (Criteria) this;
        }

        public Criteria andArticoloLessThanOrEqualTo(Integer value) {
            addCriterion("articolo <=", value, "articolo");
            return (Criteria) this;
        }

        public Criteria andArticoloIn(List<Integer> values) {
            addCriterion("articolo in", values, "articolo");
            return (Criteria) this;
        }

        public Criteria andArticoloNotIn(List<Integer> values) {
            addCriterion("articolo not in", values, "articolo");
            return (Criteria) this;
        }

        public Criteria andArticoloBetween(Integer value1, Integer value2) {
            addCriterion("articolo between", value1, value2, "articolo");
            return (Criteria) this;
        }

        public Criteria andArticoloNotBetween(Integer value1, Integer value2) {
            addCriterion("articolo not between", value1, value2, "articolo");
            return (Criteria) this;
        }

        public Criteria andFatturaIsNull() {
            addCriterion("fattura is null");
            return (Criteria) this;
        }

        public Criteria andFatturaIsNotNull() {
            addCriterion("fattura is not null");
            return (Criteria) this;
        }

        public Criteria andFatturaEqualTo(Integer value) {
            addCriterion("fattura =", value, "fattura");
            return (Criteria) this;
        }

        public Criteria andFatturaNotEqualTo(Integer value) {
            addCriterion("fattura <>", value, "fattura");
            return (Criteria) this;
        }

        public Criteria andFatturaGreaterThan(Integer value) {
            addCriterion("fattura >", value, "fattura");
            return (Criteria) this;
        }

        public Criteria andFatturaGreaterThanOrEqualTo(Integer value) {
            addCriterion("fattura >=", value, "fattura");
            return (Criteria) this;
        }

        public Criteria andFatturaLessThan(Integer value) {
            addCriterion("fattura <", value, "fattura");
            return (Criteria) this;
        }

        public Criteria andFatturaLessThanOrEqualTo(Integer value) {
            addCriterion("fattura <=", value, "fattura");
            return (Criteria) this;
        }

        public Criteria andFatturaIn(List<Integer> values) {
            addCriterion("fattura in", values, "fattura");
            return (Criteria) this;
        }

        public Criteria andFatturaNotIn(List<Integer> values) {
            addCriterion("fattura not in", values, "fattura");
            return (Criteria) this;
        }

        public Criteria andFatturaBetween(Integer value1, Integer value2) {
            addCriterion("fattura between", value1, value2, "fattura");
            return (Criteria) this;
        }

        public Criteria andFatturaNotBetween(Integer value1, Integer value2) {
            addCriterion("fattura not between", value1, value2, "fattura");
            return (Criteria) this;
        }

        public Criteria andListinoIsNull() {
            addCriterion("listino is null");
            return (Criteria) this;
        }

        public Criteria andListinoIsNotNull() {
            addCriterion("listino is not null");
            return (Criteria) this;
        }

        public Criteria andListinoEqualTo(Integer value) {
            addCriterion("listino =", value, "listino");
            return (Criteria) this;
        }

        public Criteria andListinoNotEqualTo(Integer value) {
            addCriterion("listino <>", value, "listino");
            return (Criteria) this;
        }

        public Criteria andListinoGreaterThan(Integer value) {
            addCriterion("listino >", value, "listino");
            return (Criteria) this;
        }

        public Criteria andListinoGreaterThanOrEqualTo(Integer value) {
            addCriterion("listino >=", value, "listino");
            return (Criteria) this;
        }

        public Criteria andListinoLessThan(Integer value) {
            addCriterion("listino <", value, "listino");
            return (Criteria) this;
        }

        public Criteria andListinoLessThanOrEqualTo(Integer value) {
            addCriterion("listino <=", value, "listino");
            return (Criteria) this;
        }

        public Criteria andListinoIn(List<Integer> values) {
            addCriterion("listino in", values, "listino");
            return (Criteria) this;
        }

        public Criteria andListinoNotIn(List<Integer> values) {
            addCriterion("listino not in", values, "listino");
            return (Criteria) this;
        }

        public Criteria andListinoBetween(Integer value1, Integer value2) {
            addCriterion("listino between", value1, value2, "listino");
            return (Criteria) this;
        }

        public Criteria andListinoNotBetween(Integer value1, Integer value2) {
            addCriterion("listino not between", value1, value2, "listino");
            return (Criteria) this;
        }

        public Criteria andQuantitaIsNull() {
            addCriterion("quantita is null");
            return (Criteria) this;
        }

        public Criteria andQuantitaIsNotNull() {
            addCriterion("quantita is not null");
            return (Criteria) this;
        }

        public Criteria andQuantitaEqualTo(Integer value) {
            addCriterion("quantita =", value, "quantita");
            return (Criteria) this;
        }

        public Criteria andQuantitaNotEqualTo(Integer value) {
            addCriterion("quantita <>", value, "quantita");
            return (Criteria) this;
        }

        public Criteria andQuantitaGreaterThan(Integer value) {
            addCriterion("quantita >", value, "quantita");
            return (Criteria) this;
        }

        public Criteria andQuantitaGreaterThanOrEqualTo(Integer value) {
            addCriterion("quantita >=", value, "quantita");
            return (Criteria) this;
        }

        public Criteria andQuantitaLessThan(Integer value) {
            addCriterion("quantita <", value, "quantita");
            return (Criteria) this;
        }

        public Criteria andQuantitaLessThanOrEqualTo(Integer value) {
            addCriterion("quantita <=", value, "quantita");
            return (Criteria) this;
        }

        public Criteria andQuantitaIn(List<Integer> values) {
            addCriterion("quantita in", values, "quantita");
            return (Criteria) this;
        }

        public Criteria andQuantitaNotIn(List<Integer> values) {
            addCriterion("quantita not in", values, "quantita");
            return (Criteria) this;
        }

        public Criteria andQuantitaBetween(Integer value1, Integer value2) {
            addCriterion("quantita between", value1, value2, "quantita");
            return (Criteria) this;
        }

        public Criteria andQuantitaNotBetween(Integer value1, Integer value2) {
            addCriterion("quantita not between", value1, value2, "quantita");
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

        public Criteria andImportoIsNull() {
            addCriterion("importo is null");
            return (Criteria) this;
        }

        public Criteria andImportoIsNotNull() {
            addCriterion("importo is not null");
            return (Criteria) this;
        }

        public Criteria andImportoEqualTo(Double value) {
            addCriterion("importo =", value, "importo");
            return (Criteria) this;
        }

        public Criteria andImportoNotEqualTo(Double value) {
            addCriterion("importo <>", value, "importo");
            return (Criteria) this;
        }

        public Criteria andImportoGreaterThan(Double value) {
            addCriterion("importo >", value, "importo");
            return (Criteria) this;
        }

        public Criteria andImportoGreaterThanOrEqualTo(Double value) {
            addCriterion("importo >=", value, "importo");
            return (Criteria) this;
        }

        public Criteria andImportoLessThan(Double value) {
            addCriterion("importo <", value, "importo");
            return (Criteria) this;
        }

        public Criteria andImportoLessThanOrEqualTo(Double value) {
            addCriterion("importo <=", value, "importo");
            return (Criteria) this;
        }

        public Criteria andImportoIn(List<Double> values) {
            addCriterion("importo in", values, "importo");
            return (Criteria) this;
        }

        public Criteria andImportoNotIn(List<Double> values) {
            addCriterion("importo not in", values, "importo");
            return (Criteria) this;
        }

        public Criteria andImportoBetween(Double value1, Double value2) {
            addCriterion("importo between", value1, value2, "importo");
            return (Criteria) this;
        }

        public Criteria andImportoNotBetween(Double value1, Double value2) {
            addCriterion("importo not between", value1, value2, "importo");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table articolo_fattura
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
     * This class corresponds to the database table articolo_fattura
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