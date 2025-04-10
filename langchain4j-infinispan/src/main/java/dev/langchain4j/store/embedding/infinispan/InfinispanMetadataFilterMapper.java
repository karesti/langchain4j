package dev.langchain4j.store.embedding.infinispan;

import dev.langchain4j.store.embedding.filter.Filter;
import dev.langchain4j.store.embedding.filter.comparison.IsEqualTo;
import dev.langchain4j.store.embedding.filter.comparison.IsGreaterThan;
import dev.langchain4j.store.embedding.filter.comparison.IsGreaterThanOrEqualTo;
import dev.langchain4j.store.embedding.filter.comparison.IsIn;
import dev.langchain4j.store.embedding.filter.comparison.IsLessThan;
import dev.langchain4j.store.embedding.filter.comparison.IsLessThanOrEqualTo;
import dev.langchain4j.store.embedding.filter.comparison.IsNotEqualTo;
import dev.langchain4j.store.embedding.filter.comparison.IsNotIn;
import dev.langchain4j.store.embedding.filter.logical.And;
import dev.langchain4j.store.embedding.filter.logical.Not;
import dev.langchain4j.store.embedding.filter.logical.Or;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

class InfinispanMetadataFilterMapper {

    static String map(Filter filter) {
        String filterQuery = "";
        if (filter instanceof IsEqualTo) {
            filterQuery = mapEqual((IsEqualTo) filter);
        } else if (filter instanceof IsNotEqualTo) {
            filterQuery = mapNotEqual((IsNotEqualTo) filter);
//        } else if (filter instanceof IsGreaterThan) {
//            return mapGreaterThan((IsGreaterThan) filter);
//        } else if (filter instanceof IsGreaterThanOrEqualTo) {
//            return mapGreaterThanOrEqual((IsGreaterThanOrEqualTo) filter);
//        } else if (filter instanceof IsLessThan) {
//            return mapLessThan((IsLessThan) filter);
//        } else if (filter instanceof IsLessThanOrEqualTo) {
//            return mapLessThanOrEqual((IsLessThanOrEqualTo) filter);
//        } else if (filter instanceof IsIn) {
//            return mapIn((IsIn) filter);
//        } else if (filter instanceof IsNotIn) {
//            return mapNotIn((IsNotIn) filter);
//        } else if (filter instanceof And) {
//            return mapAnd((And) filter);
//        } else if (filter instanceof Not) {
//            return mapNot((Not) filter);
//        } else if (filter instanceof Or) {
//            return mapOr((Or) filter);
        } else {
            throw new UnsupportedOperationException("Unsupported filter type: " + filter.getClass().getName());
        }
        return " filtering("  + filterQuery + ")";
    }

    private static String mapEqual(IsEqualTo filter) {
        return metadataKey(filter.key()) + "i.metadata.value = '" + filter.comparisonValue() + "'";
    }

    private static String mapNotEqual(IsNotEqualTo filter) {
        return metadataKey(filter.key()) + "i.metadata.value != '" + filter.comparisonValue() + "'";
    }

    private static String metadataKey(String key) {
        return " i.metadata.name = '" + key + "' and ";
    }

//    private static Query mapGreaterThan(IsGreaterThan isGreaterThan) {
//        return new Query.Builder().bool(b -> b.filter(f -> f.range(r ->
//                r.field("metadata." + isGreaterThan.key())
//                        .gt(JsonData.of(isGreaterThan.comparisonValue()))
//        ))).build();
//    }
//
//    private static Query mapGreaterThanOrEqual(IsGreaterThanOrEqualTo isGreaterThanOrEqualTo) {
//        return new Query.Builder().bool(b -> b.filter(f -> f.range(r ->
//                r.field("metadata." + isGreaterThanOrEqualTo.key())
//                        .gte(JsonData.of(isGreaterThanOrEqualTo.comparisonValue()))
//        ))).build();
//    }
//
//    private static Query mapLessThan(IsLessThan isLessThan) {
//        return new Query.Builder().bool(b -> b.filter(f -> f.range(r ->
//                r.field("metadata." + isLessThan.key())
//                        .lt(JsonData.of(isLessThan.comparisonValue()))
//        ))).build();
//    }
//
//    private static Query mapLessThanOrEqual(IsLessThanOrEqualTo isLessThanOrEqualTo) {
//        return new Query.Builder().bool(b -> b.filter(f -> f.range(r ->
//                r.field("metadata." + isLessThanOrEqualTo.key())
//                        .lte(JsonData.of(isLessThanOrEqualTo.comparisonValue()))
//        ))).build();
//    }
//
//    public static Query mapIn(IsIn isIn) {
//        return new Query.Builder().bool(b -> b.filter(f -> f.terms(t ->
//                t.field(formatKey(isIn.key(), isIn.comparisonValues()))
//                        .terms(terms -> {
//                            List<FieldValue> values = isIn.comparisonValues().stream()
//                                    .map(it -> FieldValue.of(JsonData.of(it)))
//                                    .collect(toList());
//                            return terms.value(values);
//                        })
//        ))).build();
//    }
//
//    public static Query mapNotIn(IsNotIn isNotIn) {
//        return new Query.Builder().bool(b -> b.mustNot(mn -> mn.terms(t ->
//                t.field(formatKey(isNotIn.key(), isNotIn.comparisonValues()))
//                        .terms(terms -> {
//                            List<FieldValue> values = isNotIn.comparisonValues().stream()
//                                    .map(it -> FieldValue.of(JsonData.of(it)))
//                                    .collect(toList());
//                            return terms.value(values);
//                        })
//        ))).build();
//    }
//
//    private static Query mapAnd(And and) {
//        BoolQuery boolQuery = new BoolQuery.Builder()
//                .must(map(and.left()))
//                .must(map(and.right()))
//                .build();
//        return new Query.Builder().bool(boolQuery).build();
//    }
//
//    private static Query mapNot(Not not) {
//        BoolQuery boolQuery = new BoolQuery.Builder()
//                .mustNot(map(not.expression()))
//                .build();
//        return new Query.Builder().bool(boolQuery).build();
//    }
//
//    private static Query mapOr(Or or) {
//        BoolQuery boolQuery = new BoolQuery.Builder()
//                .should(map(or.left()))
//                .should(map(or.right()))
//                .build();
//        return new Query.Builder().bool(boolQuery).build();
//    }

}

