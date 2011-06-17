package com.guiwuu.design.container;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.guiwuu.design.container.ioc.guice.CopyModule;
import com.guiwuu.design.container.ioc.guice.GuiceCopier;
import com.guiwuu.design.container.ioc.simple.ApplicationContext;
import com.guiwuu.design.container.servicelocator.BeanFactory;
import com.guiwuu.design.ood.dip.Copier;
import com.guiwuu.design.ood.dip.Reader;
import com.guiwuu.design.ood.dip.Writer;

public class CopierContainer {

    public Copier newCopier(Reader reader, Writer writer) {
        return new Copier(reader, writer);
    }

    public Copier newCopierByReflect(String readerName, String writerName) throws Exception {
        Class readerClass = Class.forName(readerName);
        Class writerClass = Class.forName(writerName);
        return new Copier((Reader) readerClass.newInstance(), (Writer) writerClass.newInstance());
    }

    public Copier newCopierByServiceLocator(String readerBean, String writerBean) throws Exception {
        Reader reader = (Reader) BeanFactory.createBean(readerBean);
        Writer writer = (Writer) BeanFactory.createBean(writerBean);
        return new Copier(reader, writer);
    }

    public Copier newCopierBySimpleIoc() throws Exception {
        return (Copier) ApplicationContext.getBean("copier");
    }

    public Copier newCopierByGuice() throws Exception {
        Injector injector = Guice.createInjector(new CopyModule());
        return injector.getInstance(GuiceCopier.class);
    }
}
