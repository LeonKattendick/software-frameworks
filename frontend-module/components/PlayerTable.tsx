import { gameTypeToPrintableName } from '@/util/GameType';
import { Player } from '@/util/interfaces/Player';
import { Table } from 'antd';
import Link from 'next/link';

export const PlayerTable = ({ players, isAll }: { players: Player[]; isAll: boolean }) => {
  return (
    <Table dataSource={players} size="small" bordered pagination={false} scroll={{ y: 600 }} style={{ minHeight: 600 }}>
      <Table.Column title="ID" dataIndex="player_id" width="15%" />
      <Table.Column title="Name" render={(_, record: Player) => <Link href={`/player/${record.player_id}`}>{record.name}</Link>} />
      {isAll && <Table.Column title="Spiel" render={(_, record: Player) => gameTypeToPrintableName(record.game_type)} />}
      <Table.Column title="KDA" render={(_, record: Player) => record.global_kda.toFixed(2)} />
    </Table>
  );
};
