package eod;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import eod.predicate.Predicate;

import static eod.predicate.Predicates.filter;

@SupportedAnnotationTypes("eod.NoNull")
public final class NoNullProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (TypeElement type : annotations) {
			for (Element element : roundEnv.getElementsAnnotatedWith(type)) {
				roundEnv.getRootElements().
			}
		}
		return true;
	}

}