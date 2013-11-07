/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package fixture.simple;

import org.apache.isis.applib.fixtures.AbstractFixture;
import org.apache.isis.objectstore.jdo.applib.service.support.IsisJdoSupport;

import services.ClockService;
import dom.simple.ConferenceSession;
import dom.simple.ConferenceSessions;

public class ConferenceSessionsFixture extends AbstractFixture {

    
    @Override
    public void install() {

        isisJdoSupport.executeUpdate("delete from \"ConferenceSession\"");

        installObjects();
        
        getContainer().flush();
    }

    private void installObjects() {

        create("RRRADDD! Apache Isis");
        create("Best practices for AngularJS");
        create("Refactor your specs!");
        create("Why Kotlin?");
        create("Introduction to the Play Framework");
        create("Object Oriented Design in the Wild");
    }


    // //////////////////////////////////////

    private ConferenceSession create(final String name) {
        ConferenceSession session = conferenceSessions.create(name);
        session.setDate(clockService.now().plusDays((int)(Math.random()*5)));
        return session;
    }


    // //////////////////////////////////////


    private ConferenceSessions conferenceSessions;

    public void injectConferenceSessions(final ConferenceSessions conferenceSessions) {
        this.conferenceSessions = conferenceSessions;
    }

    
    private IsisJdoSupport isisJdoSupport;
    public void injectIsisJdoSupport(IsisJdoSupport isisJdoSupport) {
        this.isisJdoSupport = isisJdoSupport;
    }

    
    private ClockService clockService;

    public final void injectClockService(final ClockService clockService) {
        this.clockService = clockService;
    }

}
