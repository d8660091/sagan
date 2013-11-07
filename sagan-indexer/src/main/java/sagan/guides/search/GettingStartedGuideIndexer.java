package sagan.guides.search;

import sagan.guides.GettingStartedGuide;
import sagan.guides.support.GettingStartedGuides;
import sagan.search.service.SearchService;
import sagan.util.index.Indexer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GettingStartedGuideIndexer implements Indexer<GettingStartedGuide> {

    private GuideSearchEntryMapper mapper = new GuideSearchEntryMapper();

    private final SearchService searchService;
    private final GettingStartedGuides gsGuides;

    @Autowired
    public GettingStartedGuideIndexer(SearchService searchService, GettingStartedGuides gsGuides) {
        this.searchService = searchService;
        this.gsGuides = gsGuides;
    }

    @Override
    public Iterable<GettingStartedGuide> indexableItems() {
        return gsGuides.findAll();
    }

    @Override
    public void indexItem(GettingStartedGuide guide) {
        searchService.saveToIndex(mapper.map(guide));
    }

    @Override
    public String counterName() {
        return "getting_started_guides";
    }

    @Override
    public String getId(GettingStartedGuide indexable) {
        return indexable.getGuideId();
    }
}