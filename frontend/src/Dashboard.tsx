import * as React from 'react';
import { useState, useEffect } from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import { Button, TextField } from '@mui/material';
import Chart from 'chart.js/auto';

const Dashboard: React.FC = () => {
    const [cryptoName, setCryptoName] = useState<string>('');
    const [cryptoData, setCryptoData] = useState<any>(null);
    const [chartInstance, setChartInstance] = useState<Chart | null>(null);

    const fetchData = () => {
        fetch(`http://localhost:8080/api/crypto/search/${cryptoName}`)
            .then(response => response.json())
            .then(data => {
                setCryptoData(data);
            })
            .catch(error => console.error('Error fetching crypto data:', error));
    };

    const fetchHistoricalData = () => {
        fetch(`http://localhost:8080/api/crypto/historical-data/${cryptoName}`)
            .then(response => response.json())
            .then(histData => {
                if (histData && histData.prices) {
                    const prices = histData.prices.map((item: [number, number]) => item[1]);
                    const timestamps = histData.prices.map((item: [number, number]) =>
                        new Date(item[0]).toLocaleDateString()
                    );

                    renderChart(timestamps, prices, cryptoData?.name || 'Crypto');
                }
            })
            .catch(error => {
                console.error('Error fetching historical data:', error);
            });
    };

    const renderChart = (labels: string[], data: number[], cryptoName: string) => {
        if (chartInstance) {
            chartInstance.destroy(); // Zerstöre vorhandenes Diagramm, um es zu aktualisieren
        }

        const ctx = document.getElementById('crypto-chart') as HTMLCanvasElement;

        if (ctx) {
            const newChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels,
                    datasets: [
                        {
                            label: `${cryptoName} Price`,
                            data,
                            borderColor: 'rgba(75, 192, 192, 1)',
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            fill: true,
                        },
                    ],
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            display: true,
                        },
                    },
                    scales: {
                        x: {
                            title: {
                                display: true,
                                text: 'Date',
                            },
                        },
                        y: {
                            title: {
                                display: true,
                                text: 'Price (USD)',
                            },
                        },
                    },
                },
            });

            setChartInstance(newChart);
        }
    };

    return (
        <Box
            sx={{
                py: 4,
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                textAlign: 'center',
                gap: 2,
            }}
        >
            <Typography variant="h4">Crypto Dashboard</Typography>
            <TextField
                id="crypto-name-input"
                label="Enter Crypto Name"
                variant="outlined"
                onChange={(e) => setCryptoName(e.target.value.toLowerCase())}
            />
            <Button variant="contained" onClick={fetchData}>
                Search
            </Button>
            <Button variant="outlined" onClick={fetchHistoricalData} disabled={!cryptoData}>
                Load Historical Data
            </Button>
            <canvas id="crypto-chart" width="600" height="400"></canvas>
        </Box>
    );
};

export default Dashboard;
