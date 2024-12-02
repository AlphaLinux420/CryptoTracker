import Box from "@mui/material/Box";
import { useEffect, useState } from "react";
import {
    LineChart,
    Line,
    CartesianGrid,
    XAxis,
    YAxis,
    Tooltip,
    ResponsiveContainer,
} from "recharts";
import dayjs from "dayjs";

function Chart({ name }: { name: string }) {
    const [data, setData] = useState<any[]>([]);
    const [formattedData, setFormattedData] = useState<any[]>([]);

    useEffect(() => {
        addDataToDB();
    }, []);

    useEffect(() => {
        if (data.length > 0) {
            const newData = data
                .filter((element, index) =>
                    element.id === 1 || index % 100 === 0 || element.id === data.length - 1
                )
                .map((element) => ({
                    price: element.close,
                    time: element.closetime,
                }));
            setFormattedData(newData);
        }
    }, [data]);

    const getSymbol = () => {
        if (name === "Bitcoin") {
            return "BTCUSDT";
        }else if (name === "Ethereum") {
            return "ETHUSDT";
        }else if(name=== "Solana"){
            return "SOLUSDT";
        }
        return "BTCUSDT"; // Default value for other names
    };

    const addDataToDB = () => {
        fetch(
            `http://localhost:8080/api/crypto/cryptoData/get?symbol=${getSymbol()}&interval=1h&startTime=${
                Date.now() - 24 * 60 * 60 * 1000
            }&endTime=${Date.now()}`,
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
            }
        )
            .then((response) => response.json())
            .then((data) => {
                setData(data);
            });
    };

    return (
        <Box>
            <h1>Chart for {name}</h1>
            <ResponsiveContainer width="100%" height={400}>
                <LineChart data={formattedData}>
                    <Line type="monotone" dataKey="price" stroke="#8884d8" />
                    <CartesianGrid stroke="#ccc" />
                    <XAxis
                        dataKey="time"
                        tickFormatter={(time) => dayjs(time).format("HH:mm")}
                    />
                    <YAxis />
                    <Tooltip
                        labelFormatter={(label) => dayjs(label).format("HH:mm")}
                    />
                </LineChart>
            </ResponsiveContainer>
        </Box>
    );
}

export default Chart;
