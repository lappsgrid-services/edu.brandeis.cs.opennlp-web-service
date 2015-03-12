package edu.brandeis.cs.lappsgrid.opennlp;

import edu.brandeis.cs.lappsgrid.api.opennlp.IPOSTagger;
import opennlp.tools.util.Sequence;

/**
 * <i>POSTagger.java</i> Language Application Grids (<b>LAPPS</b>)
 * <p> 
 * <p><a href="http://opennlp.sourceforge.net/models-1.5/">Models for 1.5 series</a>
 * <p> 
 *
 * @author Chunqi Shi ( <i>shicq@cs.brandeis.edu</i> )<br>Nov 20, 2013<br>
 * 
 */
public class POSTagger extends OpenNLPAbstractWebService implements IPOSTagger  {
    
    private static opennlp.tools.postag.POSTagger postagger;
    
    
	public POSTagger() throws OpenNLPWebServiceException {
        if (postagger == null) {
            init();
            postagger = loadPOSTagger(registModelMap.get(this.getClass()));
        }
	}
//
//
//    @Override
//    public Data execute(Data  data) {
//        String discriminatorstr = data.getDiscriminator();
//        long discriminator = DiscriminatorRegistry.get(discriminatorstr);
//        if (discriminator == Types.ERROR)
//        {
//            return data;
//        } else if (discriminator == Types.JSON) {
//            String jsonstr = data.getPayload();
//            JsonTaggerSerialization json = new JsonTaggerSerialization(jsonstr);
//            json.setProducer(this.getClass().getName() + ":" + VERSION);
//            json.setType("tagger:opennlp");
//            List<JSONObject> tokenObjs = json.findLastAnnotations();
//            if (tokenObjs == null) {
//                String message = "Invalid JSON input. Expected annotation type: " + json.getLastAnnotationType();
//                logger.warn(message);
//                return DataFactory.error(message);
//            }
//
//            String[] tokens = new String[tokenObjs.size()];
//            for(int i = 0; i < tokens.length; i++ ) {
//                tokens[i] = json.getAnnotationTextValue(tokenObjs.get(i));
//            }
//
//            String[] tags = postagger.tag(tokens);
//
//            for(int i = 0; i < tokenObjs.size(); i++) {
//                JSONObject annotation = json.newAnnotation(tokenObjs.get(i));
//                json.setCategory(annotation, tags[i]);
//            }
//            return DataFactory.json(json.toString());
//        } else  if (discriminator == Types.TEXT) {
//            String textvalue = data.getPayload();
//            JsonTaggerSerialization json = new JsonTaggerSerialization();
//            json.setProducer(this.getClass().getName() + ":" + VERSION);
//            json.setType("tagger:opennlp");
//            json.setTextValue(textvalue);
//
//            String [] tags = tag(new String[]{textvalue});
//            for(int i = 0; i < tags.length; i++) {
//                JSONObject annotation =  json.newAnnotation();
//                json.setStart(annotation, 0);
//                json.setEnd(annotation, textvalue.length());
//                json.setCategory(annotation, tags[i]);
//            }
//            return DataFactory.json(json.toString());
//
//        } else {
//            String name = DiscriminatorRegistry.get(discriminator);
//            String message = "Invalid input type. Expected JSON but found " + name;
//            logger.warn(message);
//            return DataFactory.error(message);
//        }
//    }


	@Override
	public String[] tag(String[] sentence) {
		if (postagger == null) {
			try {
				init();
			} catch (OpenNLPWebServiceException e) {
				throw new RuntimeException("tokenize(): Fail to initialize POSTagger", e);
			}
		}
		String tags[] = postagger.tag(sentence);
		return tags;
	}


	@Override
	public Sequence[] topKSequences(String[] sentence) {
		if (postagger == null) {
			try {
				init();
			} catch (OpenNLPWebServiceException e) {
				throw new RuntimeException("tokenize(): Fail to initialize POSTagger", e);
			}
		}
		Sequence tags[] = postagger.topKSequences(sentence);
		return tags;
	}

    @Override
    public String execute(String s) {
        return null;
    }

    @Override
    public String getMetadata() {
        return null;
    }
}
