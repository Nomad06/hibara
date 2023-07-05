package com.bubalex.hibara;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.ngram.NGramTokenizerFactory;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurationContext;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurer;

//public class VisalexFulltextSearchAnalysisConfigurer implements LuceneAnalysisConfigurer {
//
//    @Override
//    public void configure(LuceneAnalysisConfigurationContext luceneAnalysisConfigurationContext) {
//        luceneAnalysisConfigurationContext.analyzer("ngram").custom()
//                .tokenizer(NGramTokenizerFactory.class)
//                .tokenFilter(NGramFilterFactory.class)
//                .param("minGramSize", "1")
//                .param("maxGramSize", "4")
//                .tokenFilter(LowerCaseFilterFactory.class);
//    }
//}
