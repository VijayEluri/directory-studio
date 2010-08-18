/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */

package org.apache.directory.studio.ldapservers.model;


import org.eclipse.ui.IActionFilter;


/**
 * This class implements an {@link IActionFilter} adapter for the {@link LdapServer} class.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class LdapServerActionFilterAdapter implements IActionFilter
{
    // Identifier and value strings
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final Object STATUS = "status";
    private static final Object STATUS_STARTED = "started";
    private static final Object STATUS_STARTING = "starting";
    private static final Object STATUS_STOPPED = "stopped";
    private static final Object STATUS_STOPPING = "stopping";
    private static final Object STATUS_UNKNOWN = "unknown";
    private static final Object EXTENSION_ID = "extensionId";
    private static final Object EXTENSION_NAME = "extensionName";
    private static final Object EXTENSION_VERSION = "extensionVersion";
    private static final Object EXTENSION_VENDOR = "extensionVendor";

    /** The class instance */
    private static LdapServerActionFilterAdapter INSTANCE = new LdapServerActionFilterAdapter();


    /**
     * Private constructor.
     */
    private LdapServerActionFilterAdapter()
    {
        // Nothing to initialize
    }


    /**
     * Returns an instance of {@link LdapServerActionFilterAdapter}.
     *
     * @return
     *      an instance of {@link LdapServerActionFilterAdapter}
     */
    public static LdapServerActionFilterAdapter getInstance()
    {
        return INSTANCE;
    }


    /**
     * {@inheritDoc}
     */
    public boolean testAttribute( Object target, String name, String value )
    {
        if ( target instanceof LdapServer )
        {
            LdapServer server = ( LdapServer ) target;

            // ID
            if ( ID.equals( name ) )
            {
                return value.equals( server.getId() );
            }
            // NAME
            else if ( NAME.equals( name ) )
            {
                return value.equals( server.getName() );
            }
            // STATUS
            else if ( STATUS.equals( name ) )
            {
                switch ( server.getStatus() )
                {
                    case STARTED:
                        return value.equals( STATUS_STARTED );
                    case STARTING:
                        return value.equals( STATUS_STARTING );
                    case STOPPED:
                        return value.equals( STATUS_STOPPED );
                    case STOPPING:
                        return value.equals( STATUS_STOPPING );
                    case UNKNOWN:
                        return value.equals( STATUS_UNKNOWN );
                }
            }
            // EXTENSION ID
            else if ( EXTENSION_ID.equals( name ) )
            {
                if ( server.getLdapServerAdapterExtension() != null )
                {
                    return value.equals( server.getLdapServerAdapterExtension().getId() );
                }
            }
            // EXTENSION NAME
            else if ( EXTENSION_NAME.equals( name ) )
            {
                if ( server.getLdapServerAdapterExtension() != null )
                {
                    return value.equals( server.getLdapServerAdapterExtension().getName() );
                }
            }
            // EXTENSION VERSION
            else if ( EXTENSION_VERSION.equals( name ) )
            {
                if ( server.getLdapServerAdapterExtension() != null )
                {
                    return value.equals( server.getLdapServerAdapterExtension().getVersion() );
                }
            }
            // EXTENSION VENDOR
            else if ( EXTENSION_VENDOR.equals( name ) )
            {
                if ( server.getLdapServerAdapterExtension() != null )
                {
                    return value.equals( server.getLdapServerAdapterExtension().getVendor() );
                }
            }
        }

        return false;
    }
}
