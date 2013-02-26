/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.capedwarf.common.io;

import org.jboss.marshalling.ClassResolver;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public final class MarshallingType {
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public static MarshallingType create(MarshallingConfiguration configuration) {
        return MarshallingUtils.create(configuration);
    }

    public static MarshallingType create(ClassResolver resolver) {
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setClassResolver(resolver);
        return create(configuration);
    }

    MarshallingType(Marshaller marshaller, Unmarshaller unmarshaller) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    public synchronized <T> T readObject(Class<T> clazz, byte[] bytes) {
        return MarshallingUtils.readObject(unmarshaller, clazz, bytes);
    }

    public synchronized byte[] writeObject(Object object) {
        return MarshallingUtils.writeObject(marshaller, object);
    }
}
