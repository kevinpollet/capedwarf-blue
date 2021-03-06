/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
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

package org.jboss.test.capedwarf.testsuite;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import org.jboss.test.capedwarf.common.test.TestBase;
import org.junit.After;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withDefaults;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public abstract class TestsuiteTestBase extends TestBase {
    protected boolean ignoreTearDown;

    @After
    public void tearDown() {
        if (ignoreTearDown)
            return;

        cleanup();
    }

    protected DatastoreService createDatastoreService() {
        return DatastoreServiceFactory.getDatastoreService();
    }

    protected void cleanup() {
        DatastoreService service = createDatastoreService();
        List<Entity> entities = service.prepare(new Query().setKeysOnly()).asList(withDefaults());
        for (Entity entity : entities) {
            service.delete(entity.getKey());
        }
    }
}
