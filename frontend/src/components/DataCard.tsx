import Box from "@mui/material/Box";
import {Paper, Stack} from "@mui/material";
import Typography from "@mui/material/Typography";
import Chart from "./Chart.tsx";
import StarIcon from '@mui/icons-material/Star';

function DataCard({data}: { data: any }) {
    return (
        <Paper elevation={3} sx={{width:"100%", p:"3%"}}>
            <Stack direction={"row"} justifyContent={"space-between"} width={"100%"} height={"15%"}>
                <Stack direction={"row"} justifyContent={"space-between"} alignItems={"center"} width={"15%"}>
                    <Typography variant={"h5"}>{data.name}</Typography>
                    <StarIcon/>
                </Stack>
                <img style={{width:"20%", height:"20%"}} src={data.image} alt={data.name}/>
            </Stack>
            <Chart name={data.name}/>
        </Paper>
    );
}
export default DataCard;