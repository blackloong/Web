package com.fq.entity;

import java.util.ArrayList;
import java.util.List;

public class MessageExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_message
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_message
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_message
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_message
     *
     * @mbggenerated
     */
    public MessageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_message
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_message
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_message
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_message
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_message
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_message
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_message
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_message
     *
     * @mbggenerated
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
     * This method corresponds to the database table tb_message
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_message
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_message
     *
     * @mbggenerated
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

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userid like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userid not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andMessagetypeIsNull() {
            addCriterion("messagetype is null");
            return (Criteria) this;
        }

        public Criteria andMessagetypeIsNotNull() {
            addCriterion("messagetype is not null");
            return (Criteria) this;
        }

        public Criteria andMessagetypeEqualTo(String value) {
            addCriterion("messagetype =", value, "messagetype");
            return (Criteria) this;
        }

        public Criteria andMessagetypeNotEqualTo(String value) {
            addCriterion("messagetype <>", value, "messagetype");
            return (Criteria) this;
        }

        public Criteria andMessagetypeGreaterThan(String value) {
            addCriterion("messagetype >", value, "messagetype");
            return (Criteria) this;
        }

        public Criteria andMessagetypeGreaterThanOrEqualTo(String value) {
            addCriterion("messagetype >=", value, "messagetype");
            return (Criteria) this;
        }

        public Criteria andMessagetypeLessThan(String value) {
            addCriterion("messagetype <", value, "messagetype");
            return (Criteria) this;
        }

        public Criteria andMessagetypeLessThanOrEqualTo(String value) {
            addCriterion("messagetype <=", value, "messagetype");
            return (Criteria) this;
        }

        public Criteria andMessagetypeLike(String value) {
            addCriterion("messagetype like", value, "messagetype");
            return (Criteria) this;
        }

        public Criteria andMessagetypeNotLike(String value) {
            addCriterion("messagetype not like", value, "messagetype");
            return (Criteria) this;
        }

        public Criteria andMessagetypeIn(List<String> values) {
            addCriterion("messagetype in", values, "messagetype");
            return (Criteria) this;
        }

        public Criteria andMessagetypeNotIn(List<String> values) {
            addCriterion("messagetype not in", values, "messagetype");
            return (Criteria) this;
        }

        public Criteria andMessagetypeBetween(String value1, String value2) {
            addCriterion("messagetype between", value1, value2, "messagetype");
            return (Criteria) this;
        }

        public Criteria andMessagetypeNotBetween(String value1, String value2) {
            addCriterion("messagetype not between", value1, value2, "messagetype");
            return (Criteria) this;
        }

        public Criteria andPushtypeIsNull() {
            addCriterion("pushtype is null");
            return (Criteria) this;
        }

        public Criteria andPushtypeIsNotNull() {
            addCriterion("pushtype is not null");
            return (Criteria) this;
        }

        public Criteria andPushtypeEqualTo(String value) {
            addCriterion("pushtype =", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeNotEqualTo(String value) {
            addCriterion("pushtype <>", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeGreaterThan(String value) {
            addCriterion("pushtype >", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeGreaterThanOrEqualTo(String value) {
            addCriterion("pushtype >=", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeLessThan(String value) {
            addCriterion("pushtype <", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeLessThanOrEqualTo(String value) {
            addCriterion("pushtype <=", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeLike(String value) {
            addCriterion("pushtype like", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeNotLike(String value) {
            addCriterion("pushtype not like", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeIn(List<String> values) {
            addCriterion("pushtype in", values, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeNotIn(List<String> values) {
            addCriterion("pushtype not in", values, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeBetween(String value1, String value2) {
            addCriterion("pushtype between", value1, value2, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeNotBetween(String value1, String value2) {
            addCriterion("pushtype not between", value1, value2, "pushtype");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andPushdateIsNull() {
            addCriterion("pushdate is null");
            return (Criteria) this;
        }

        public Criteria andPushdateIsNotNull() {
            addCriterion("pushdate is not null");
            return (Criteria) this;
        }

        public Criteria andPushdateEqualTo(String value) {
            addCriterion("pushdate =", value, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateNotEqualTo(String value) {
            addCriterion("pushdate <>", value, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateGreaterThan(String value) {
            addCriterion("pushdate >", value, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateGreaterThanOrEqualTo(String value) {
            addCriterion("pushdate >=", value, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateLessThan(String value) {
            addCriterion("pushdate <", value, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateLessThanOrEqualTo(String value) {
            addCriterion("pushdate <=", value, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateLike(String value) {
            addCriterion("pushdate like", value, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateNotLike(String value) {
            addCriterion("pushdate not like", value, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateIn(List<String> values) {
            addCriterion("pushdate in", values, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateNotIn(List<String> values) {
            addCriterion("pushdate not in", values, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateBetween(String value1, String value2) {
            addCriterion("pushdate between", value1, value2, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateNotBetween(String value1, String value2) {
            addCriterion("pushdate not between", value1, value2, "pushdate");
            return (Criteria) this;
        }

        public Criteria andIsviewedIsNull() {
            addCriterion("isviewed is null");
            return (Criteria) this;
        }

        public Criteria andIsviewedIsNotNull() {
            addCriterion("isviewed is not null");
            return (Criteria) this;
        }

        public Criteria andIsviewedEqualTo(String value) {
            addCriterion("isviewed =", value, "isviewed");
            return (Criteria) this;
        }

        public Criteria andIsviewedNotEqualTo(String value) {
            addCriterion("isviewed <>", value, "isviewed");
            return (Criteria) this;
        }

        public Criteria andIsviewedGreaterThan(String value) {
            addCriterion("isviewed >", value, "isviewed");
            return (Criteria) this;
        }

        public Criteria andIsviewedGreaterThanOrEqualTo(String value) {
            addCriterion("isviewed >=", value, "isviewed");
            return (Criteria) this;
        }

        public Criteria andIsviewedLessThan(String value) {
            addCriterion("isviewed <", value, "isviewed");
            return (Criteria) this;
        }

        public Criteria andIsviewedLessThanOrEqualTo(String value) {
            addCriterion("isviewed <=", value, "isviewed");
            return (Criteria) this;
        }

        public Criteria andIsviewedLike(String value) {
            addCriterion("isviewed like", value, "isviewed");
            return (Criteria) this;
        }

        public Criteria andIsviewedNotLike(String value) {
            addCriterion("isviewed not like", value, "isviewed");
            return (Criteria) this;
        }

        public Criteria andIsviewedIn(List<String> values) {
            addCriterion("isviewed in", values, "isviewed");
            return (Criteria) this;
        }

        public Criteria andIsviewedNotIn(List<String> values) {
            addCriterion("isviewed not in", values, "isviewed");
            return (Criteria) this;
        }

        public Criteria andIsviewedBetween(String value1, String value2) {
            addCriterion("isviewed between", value1, value2, "isviewed");
            return (Criteria) this;
        }

        public Criteria andIsviewedNotBetween(String value1, String value2) {
            addCriterion("isviewed not between", value1, value2, "isviewed");
            return (Criteria) this;
        }

        public Criteria andIsdeletedIsNull() {
            addCriterion("isdeleted is null");
            return (Criteria) this;
        }

        public Criteria andIsdeletedIsNotNull() {
            addCriterion("isdeleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsdeletedEqualTo(String value) {
            addCriterion("isdeleted =", value, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedNotEqualTo(String value) {
            addCriterion("isdeleted <>", value, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedGreaterThan(String value) {
            addCriterion("isdeleted >", value, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedGreaterThanOrEqualTo(String value) {
            addCriterion("isdeleted >=", value, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedLessThan(String value) {
            addCriterion("isdeleted <", value, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedLessThanOrEqualTo(String value) {
            addCriterion("isdeleted <=", value, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedLike(String value) {
            addCriterion("isdeleted like", value, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedNotLike(String value) {
            addCriterion("isdeleted not like", value, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedIn(List<String> values) {
            addCriterion("isdeleted in", values, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedNotIn(List<String> values) {
            addCriterion("isdeleted not in", values, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedBetween(String value1, String value2) {
            addCriterion("isdeleted between", value1, value2, "isdeleted");
            return (Criteria) this;
        }

        public Criteria andIsdeletedNotBetween(String value1, String value2) {
            addCriterion("isdeleted not between", value1, value2, "isdeleted");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_message
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_message
     *
     * @mbggenerated
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