import * as React from 'react';
import { useState, useEffect } from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import {Button, Stack, TextField} from '@mui/material';
import Chart, {ChartType, DefaultDataPoint} from 'chart.js/auto';
import DataCard from './components/DataCard';
import START_DATA from "../StartData.ts";

const Dashboard: React.FC = () => {
    const [cryptoData, setCryptoData] = useState<any>([]);
    useEffect(() => {
        fetchStartData();
    }, []);

    const fetchStartData = () => {
        fetch("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=10&page=1")
            .then(response => response.json())
            .then(data => {
                setCryptoData(data);
            })
            .catch(error => console.error('Error fetching start data:', error));
    }


    return (
        <Stack direction={"row"} flexWrap={"wrap"} sx={{ width:"100%"}}>
            {cryptoData&&cryptoData.map((data: any, idx:number) => (
                <Box key={idx.toString()} sx={{flexGrow:1, flexBasis:200, m:"1%"}}>
                    <DataCard data={data} />
                </Box>
            ))}

        </Stack>
    );
};

export default Dashboard;
