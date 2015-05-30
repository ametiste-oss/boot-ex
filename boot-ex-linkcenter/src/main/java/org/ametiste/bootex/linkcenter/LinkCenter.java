package org.ametiste.bootex.linkcenter;

import org.amtetiste.uttil.object.trace.Trace;

import java.util.function.Consumer;

public class LinkCenter {

    private final ControllerScaner scaner;

    private final HostResolver hostResolver;

    private final URIBuilderFactory uriBuilderFactory;


    // TODO: добавить резолвер хоста,
    // я бы хотел иметь возможность подпихивать разные резолверы
    // центр же должен выбрать тот, который может разрезолвить хост
    //
    // Например, я бы хотел сделать
    // LocalhostResolver,
    // EurekaVIPResolver,
    // DirectResolver(резолвит то, что передали в конструктор)
    // WebRequestContextResolver(по аналогии со спрингом)
    //

    // TODO: подумать, каким образом я могу поддерживать разные сканеры
    // я бы хотел дать возможность юзать и спринговый MvcUriComponentsBuilder и хатеосовский
    // ( или просто юзать их, когда запросили резолв не темплейта, а урла? )
    //

    // TODO: подумать, смогу ли я сделать резолвинг не только темплейта, но и урла с параметрами
    // ( возможно используя уже готовые резолверы )

    public LinkCenter(ControllerScaner controllerScaner,
                      HostResolver hostResolver,
                      URIBuilderFactory uriBuilderFactory) {
        this.hostResolver = hostResolver;
        this.uriBuilderFactory = uriBuilderFactory;
        this.scaner = controllerScaner;
    }

    // Метод resolveTemplate предоставляет возможность передать/сделать консамера,
    // трейс работы которого будет записан.
    public <T> String resolveTemplate(Class<T> tClass, Consumer<T> recorder) {

        final URIBuilder uriTemplateBuilder = uriBuilderFactory.createNewBuilder();

        uriTemplateBuilder.scheme("http")
                .host(hostResolver.resolveHostName());

        final Trace<T> trace = scaner
                .traceController(tClass, uriTemplateBuilder::path);

        trace.recordTrace(recorder);

        return uriTemplateBuilder.toURITemplate();
    }

}