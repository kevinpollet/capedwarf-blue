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

package org.jboss.capedwarf.channel.manager;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:marko.luksa@gmail.com">Marko Luksa</a>
 */
public class MessageNotificationTask implements Callable<Void>, Serializable {
    private final static long serialVersionUID = 1L;
    private final static Logger log = Logger.getLogger(MessageNotificationTask.class.getName());

    private String channelToken;

    public MessageNotificationTask(String channelToken) {
        this.channelToken = channelToken;
    }

    public Void call() throws Exception {
        if (ChannelQueueManager.getInstance().channelQueueExists(channelToken)) {
            log.info("Obtaining channel connection for token " + channelToken);
            ChannelQueue channelQueue = ChannelQueueManager.getInstance().getChannelQueue(channelToken);
            log.info("Notifying listeners that a message is available");
            channelQueue.notifyMessageAvailable();
        }
        return null;
    }
}

