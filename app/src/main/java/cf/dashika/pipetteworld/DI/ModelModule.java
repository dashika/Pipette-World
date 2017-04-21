package cf.dashika.pipetteworld.DI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Singleton;

import cf.dashika.pipetteworld.Model.Adobe.ColorthemeData;
import cf.dashika.pipetteworld.Model.Adobe.Element;
import cf.dashika.pipetteworld.Model.Adobe.Elements;
import cf.dashika.pipetteworld.Model.Adobe.Libraries;
import cf.dashika.pipetteworld.Model.Adobe.Library;
import cf.dashika.pipetteworld.Model.Adobe.Representation;
import cf.dashika.pipetteworld.Model.Adobe.Swatch;
import cf.dashika.pipetteworld.Model.Adobe.Value;
import cf.dashika.pipetteworld.Model.DB.ElementDB;
import cf.dashika.pipetteworld.Model.DB.ValueDB;
import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {

    @Provides
    @Singleton
    Libraries librarie() {
        Libraries libraries = new Libraries();
        migrateToLibrary(libraries);
        return libraries;
    }

    private void migrateToLibrary(Libraries libraries) {
        List<ElementDB> elementDBS = ElementDB.getAllElement();
        if(elementDBS.size() == 0) return;
        Library library = new Library();
        library.setName("saved" + new Random().nextInt());
        library.setState("my");
        library.setEtag("my");

        Elements elements = new Elements();

        for (ElementDB elementDB : elementDBS) {
            Element element = new Element();
            element.setItsAdobeObject(false);
            element.setName(elementDB.getName());
            Representation representation = new Representation();
            ColorthemeData colorthemeData = new ColorthemeData();
            List<List<Swatch>> lists = colorthemeData.getSwatches();
            for (ValueDB valueDB : elementDB.getValue()) {
                Swatch swatch = new Swatch();
                Value value = new Value();
                value.setR(valueDB.getR());
                value.setB(valueDB.getB());
                value.setG(valueDB.getG());
                swatch.setValue(value);
                List<Swatch> swatches = new ArrayList<>();
                swatches.add(swatch);

                lists.add(swatches);
            }
            representation.setColorthemeData(colorthemeData);
            element.getRepresentations().add(representation);
            elements.getElements().add(element);
        }
        library.getElements().add(elements);
        libraries.getLibraries().add(library);
    }
}
