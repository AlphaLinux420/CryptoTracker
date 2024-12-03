import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import {createTheme} from '@mui/material/styles';

import LayersIcon from '@mui/icons-material/Layers';
import {AppProvider, type Navigation, Router} from '@toolpad/core/AppProvider';
import {DashboardLayout} from '@toolpad/core/DashboardLayout';
import Dashboard from './Pages/Dashboard.tsx';
import StarIcon from '@mui/icons-material/Star';
import Favourites from "./Pages/Favourites.tsx";
import Portfolio from "./Pages/Portfolio.tsx";
import ShowChartIcon from '@mui/icons-material/ShowChart';
import News from "./Pages/News.tsx"
import NewspaperIcon from '@mui/icons-material/Newspaper';

const NAVIGATION: Navigation = [
    {kind: 'header', title: 'Main items'},
    {segment: 'dashboard', title: 'Dashboard', icon: <ShowChartIcon/>},
    {segment: 'favourite', title: 'Favourites', icon: <StarIcon/>},
    {segment: 'portfolio', title: 'Portfolios', icon: <LayersIcon/>},
    {segment: 'news', title: 'News', icon: <NewspaperIcon/>},

];

const demoTheme = createTheme({
    cssVariables: {colorSchemeSelector: 'data-toolpad-color-scheme'},
    colorSchemes: {light: true, dark: true},
    breakpoints: {values: {xs: 0, sm: 600, md: 600, lg: 1200, xl: 1536}},
});

function DemoPageContent({pathname}: { pathname: string }) {
    const components: { [key: string]: React.ReactNode } = {
        '/dashboard': <Dashboard/>,
        "/favourite": <Favourites/>,
        "/portfolio": <Portfolio/>,
        "/news": <News/>

    };

    return (
        <Box sx={{py: 4, display: 'flex', flexDirection: 'column', alignItems: 'center', textAlign: 'center'}}>
            {components[pathname] || <Typography>Page not found</Typography>}
        </Box>
    );
}


function useDemoRouter(initialPath: string): Router {
    const [pathname, setPathname] = React.useState(initialPath);

    return React.useMemo(() => ({
        pathname,
        searchParams: new URLSearchParams(),
        navigate: (path: string | URL) => setPathname(String(path)),
    }), [pathname]);
}

export default function Content() {
    const router = useDemoRouter('/dashboard');

    return (
        <AppProvider navigation={NAVIGATION} router={router} theme={demoTheme}>
            <DashboardLayout>
                <DemoPageContent pathname={router.pathname}/>
            </DashboardLayout>
        </AppProvider>
    );
}