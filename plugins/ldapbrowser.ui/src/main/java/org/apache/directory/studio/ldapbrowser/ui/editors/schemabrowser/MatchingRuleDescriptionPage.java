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

package org.apache.directory.studio.ldapbrowser.ui.editors.schemabrowser;


import org.apache.directory.api.ldap.model.schema.MatchingRule;
import org.apache.directory.api.util.Strings;
import org.apache.directory.studio.ldapbrowser.core.model.schema.Schema;
import org.apache.directory.studio.ldapbrowser.core.model.schema.SchemaUtils;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.graphics.Image;


/**
 * The MatchingRuleDescriptionPage displays a list with all
 * matching rule descriptions and hosts the detail page.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class MatchingRuleDescriptionPage extends SchemaPage
{

    /**
     * Creates a new instance of MatchingRuleDescriptionPage.
     *
     * @param schemaBrowser the schema browser
     */
    public MatchingRuleDescriptionPage( SchemaBrowser schemaBrowser )
    {
        super( schemaBrowser );
    }


    /**
     * {@inheritDoc}
     */
    protected String getTitle()
    {
        return Messages.getString( "MatchingRuleDescriptionPage.MatchingRules" ); //$NON-NLS-1$
    }


    /**
     * {@inheritDoc}
     */
    protected String getFilterDescription()
    {
        return Messages.getString( "MatchingRuleDescriptionPage.SelectMatchingRule" ); //$NON-NLS-1$
    }


    /**
     * {@inheritDoc}
     */
    protected IStructuredContentProvider getContentProvider()
    {
        return new MRDContentProvider();
    }


    /**
     * {@inheritDoc}
     */
    protected ITableLabelProvider getLabelProvider()
    {
        return new MRDLabelProvider();
    }


    /**
     * {@inheritDoc}
     */
    protected ViewerSorter getSorter()
    {
        return new MRDViewerSorter();
    }


    /**
     * {@inheritDoc}
     */
    protected ViewerFilter getFilter()
    {
        return new MRDViewerFilter();
    }


    /**
     * {@inheritDoc}
     */
    protected SchemaDetailsPage getDetailsPage()
    {
        return new MatchingRuleDescriptionDetailsPage( this, this.toolkit );
    }

    /**
     * The content provider used by the viewer.
     *
     * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
     */
    class MRDContentProvider implements IStructuredContentProvider
    {
        /**
         * {@inheritDoc}
         */
        public Object[] getElements( Object inputElement )
        {
            if ( inputElement instanceof Schema )
            {
                Schema schema = ( Schema ) inputElement;
                if ( schema != null && schema.getMatchingRuleDescriptions() != null )
                {
                    return schema.getMatchingRuleDescriptions().toArray();
                }
            }
            return new Object[0];
        }


        /**
         * {@inheritDoc}
         */
        public void dispose()
        {
        }


        /**
         * {@inheritDoc}
         */
        public void inputChanged( Viewer viewer, Object oldInput, Object newInput )
        {
        }
    }

    /**
     * The label provider used by the viewer.
     *
     * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
     */
    class MRDLabelProvider extends LabelProvider implements ITableLabelProvider
    {
        /**
         * {@inheritDoc}
         */
        public String getColumnText( Object obj, int index )
        {
            if ( obj instanceof MatchingRule )
            {
                return SchemaUtils.toString( ( MatchingRule ) obj );
            }
            return obj.toString();
        }


        /**
         * {@inheritDoc}
         */
        public Image getColumnImage( Object obj, int index )
        {
            return null;
        }
    }

    /**
     * The sorter used by the viewer.
     *
     * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
     */
    class MRDViewerSorter extends ViewerSorter
    {
        /**
         * {@inheritDoc}
         */
        public int compare( Viewer viewer, Object e1, Object e2 )
        {
            if ( e1 instanceof MatchingRule )
            {
                e1 = SchemaUtils.toString( ( MatchingRule ) e1 );
            }
            if ( e2 instanceof MatchingRule )
            {
                e2 = SchemaUtils.toString( ( MatchingRule ) e2 );
            }
            return e1.toString().compareTo( e2.toString() );
        }
    }

    /**
     * The filter used by the viewer.
     *
     * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
     */
    class MRDViewerFilter extends ViewerFilter
    {
        /**
         * {@inheritDoc}
         */
        public boolean select( Viewer viewer, Object parentElement, Object element )
        {
            if ( element instanceof MatchingRule )
            {
                MatchingRule mrd = ( MatchingRule ) element;
                boolean matched = Strings.toLowerCase( SchemaUtils.toString( mrd ) )
                    .indexOf( Strings.toLowerCase( filterText.getText() ) ) != -1
                    || Strings.toLowerCase( mrd.getOid() ).indexOf( Strings.toLowerCase( filterText.getText() ) ) != -1;
                return matched;
            }
            return false;
        }
    }

}
