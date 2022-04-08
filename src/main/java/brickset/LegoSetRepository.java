package brickset;

import repository.Repository;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Represents a repository of {@code LegoSet} objects.
 *
 * @author Zhang Haoyang
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }


    /**
     * Returns the number of Theme which is City.
     *
     * @param Theme the theme of LegoSets
     * @return value the number of Theme which is City
     */
    public long countTheme(String Theme) {
        return getAll().stream().
                filter(LegoSet -> LegoSet.getTheme() != null && LegoSet.getTheme().equals(Theme)).
                count();

    }

    /**
     * Returns the name of LegoSets which pieces below 100.
     *
     * @return String
     */
    public void LegoSetWithPieces() {
        getAll().stream().
                filter(LegoSet -> LegoSet.getPieces() < 100).
                map(LegoSet::getName).
                forEach(System.out::println);
    }

    /**
     * Returns the number of Dimensions which are null.
     *
     * @return value the number of Dimensions which are null
     */
    public long NumberOfNullDimensions(){
        return getAll().stream().
                filter(LegoSet -> LegoSet.getDimensions() == null).
                map(LegoSet::getDimensions).
                count();

    }

    /**
     * Returns the name of LegoSets which starts with B.
     *
     * @return String
     */
    public void NameOfLegoSets(){
        getAll().stream().
                filter(LegoSet -> LegoSet.getName().startsWith("B")).
                map(LegoSet::getName).
                forEach(System.out::println);
    }

    /**
     * Returns the first 10 Year of LegoSets.
     *
     * @return String
     */
    public void YearOfLegoSets(){
        getAll().stream().
                map(LegoSet::getYear).
                limit(10).
                forEach(System.out::println);
    }

    //++++++++++++++++++++++++++++++++++++++

    /**
     * Method 1
     * Return True or False for LegoSets which pieces equals 100.
     *
     * @return boolean
     */
    public boolean MatchPiece(){
        return getAll().stream().
                anyMatch(LegoSet -> LegoSet.getPieces() == 100);
    }

    /**
     * Method 2
     * Return Tags of LegoSets which is not null.
     *
     * @return String
     */
    public void MapTags(){
        getAll().stream().
                filter(LegoSet->LegoSet.getTags() != null).
                flatMap(LegoSet -> LegoSet.getTags().stream()).
                distinct().
                forEach(System.out::println);
    }

    /**
     * Method 3
     * Return the Total pieces.
     *
     * @return value the value of Total pieces
     */
    public long TotalPiece(){
        return getAll().stream().
                map(LegoSet::getPieces).
                reduce(0,(a,b)->a+b);
    }


    /**
     * Method 4
     * Return the number of each LegoSet Theme.
     *
     * @return Map each LegoSet Theme and the number of each Theme
     */
    public Map<String,Long> NumberByTheme() {
        return getAll().stream().collect(groupingBy(LegoSet::getTheme, counting()));

    }


    /**
     * Method 5
     * Return the number of each LegoSet Subtheme.
     *
     * @return Map each LegoSet Subtheme and the number of each Subtheme
     */
    public Map<String,Long> NumberBySubTheme(){
        return getAll().stream().
                filter(LegoSet->LegoSet.getSubtheme() != null).
                collect(Collectors.groupingBy(LegoSet::getSubtheme,Collectors.counting()));
    }



    public static void main(String[] args) {

        var repository = new LegoSetRepository();

        //Method 1
        System.out.printf("%d",repository.countTheme("City"));
        System.out.println("==========================");

        //Method 2
        repository.LegoSetWithPieces();
        System.out.println("==========================");

        //Method 3
        System.out.printf("%d",repository.NumberOfNullDimensions());
        System.out.println("==========================");

        //Method 4
        repository.NameOfLegoSets();
        System.out.println("==========================");

        //Method 5
        repository.YearOfLegoSets();
        System.out.println("==========================");

        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();


       //method 1
        System.out.println(repository.MatchPiece());
        System.out.println("===========================");

        //method 2
        repository.MapTags();
        System.out.println("===========================");

        //method 3
        System.out.println(repository.TotalPiece());
        System.out.println("===========================");

        //method 4
        System.out.println(repository.NumberByTheme());
        System.out.println("===========================");

        //method 5
        System.out.println(repository.NumberBySubTheme());
        System.out.println("===========================");
    }

}
