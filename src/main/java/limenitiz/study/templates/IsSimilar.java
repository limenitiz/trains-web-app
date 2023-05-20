package limenitiz.study.templates;

@FunctionalInterface
public interface IsSimilar<Object> {
    // o1 похож на o2?
    Boolean apply(Object o1, Object o2);
}
