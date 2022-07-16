package spring;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import spring.model.BuildDates;
import spring.model.Vehicle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Demo the usage of expression language with Spring Expression Language mixin.
 * Content creator can put in expression language for a given object.
 *
 * This example only supports one object type.  All expression language items are checked
 * against one object.  If they are not found an exception is thrown.
 *
 */
public class ELDemo {

    /**
     * Run main()
     * @param args
     */
    public static void main(String[] args){

        Vehicle vehicle = new Vehicle();
        vehicle.setColor("Black");
        vehicle.setYear(2016);
        vehicle.setBuildDates(new BuildDates());

        List<String> expressionList = new ArrayList<>();
        expressionList.add("buildDates.start");
        expressionList.add("color");
        expressionList.add("year");
        expressionList.add("exist");

        AtomicReference<String> content = new AtomicReference<>("The vehicle is #{color} and it was designed in the year #{year}.  We started shipping it in #{buildDates.start}. This one does not #{exist} and should come back.");
        System.out.println("\nOriginal Content:\n" + content);

        expressionList.forEach(exp -> {
            content.set(findReplace(content.get(), exp, vehicle));
        });

        System.out.println("\nNew Content:\n" + content);

    }

    /**
     * findReplace returns the modified content.  The expression language #{ } are stripped out
     * and replaced with the resolved value by calling getValue.
     * @param content
     * @param exp
     * @param type
     * @param <T>
     * @return
     */
    private static <T> String findReplace(String content, String exp, T type){
        try{
            return content.replaceAll("\\#\\{" + exp + "\\}", getValue(exp, type));
        }catch (SpelEvaluationException spelEvaluationException){
            System.out.println(spelEvaluationException.getMessageCode() + " - " + spelEvaluationException.getMessage() + "\n Did you forget a Getter and Setter?");
            return content;
        }

    }

    /**
     * getValue creates a ExpressionParser for the given object 'T'
     * Currently only checking for two types, a String and a Date.
     * @param exp
     * @param type
     * @param <T>
     * @return
     */
    private static <T> String getValue(String exp, T type) throws SpelEvaluationException {
        ExpressionParser parser = new SpelExpressionParser();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM, dd yyyy");
        String value;

        System.out.println("\nprocess '" + exp + "'");
        StandardEvaluationContext context = new StandardEvaluationContext(type);
        String typeName;

            typeName = parser.parseExpression(exp).getValueType(context).getName();
            System.out.println(typeName);
            if ("java.util.Date".equalsIgnoreCase(typeName)) {
                System.out.println("'" + exp + "' is a date");
                value = sdf.format(parser.parseExpression(exp).getValue(context));
                System.out.println("value = " + value);
            } else {
                System.out.println("'" + exp + "' is NOT a date");
                value = parser.parseExpression(exp).getValue(context).toString();
                System.out.println("value = " + value);
            }

        return value;
    }

}
